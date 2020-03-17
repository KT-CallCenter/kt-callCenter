package esdp.ktcallcenter.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Math.ceil;
import static java.math.BigDecimal.*;
import static java.math.BigDecimal.valueOf;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErlangFormula {
    private static final Random random = new Random();

    public static int getCountOfEmployees(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    private static double getErlangC(int numberOfCallsPerHour, int avgServiceTimeInMinutes, int idealUnit) {
        var erlangUnit = getErlangUnit(numberOfCallsPerHour, avgServiceTimeInMinutes);
        var x = getX(idealUnit, erlangUnit);
        var y = getY(idealUnit, erlangUnit);
        return x / (x + y);
    }

    private static int getErlangUnit(int numberOfCallsPerHour, int avgServiceTimeInMinutes) {
        var avgServiceTimeInHours = (double) avgServiceTimeInMinutes / 60;
        return (int) ceil(numberOfCallsPerHour * avgServiceTimeInHours);
    }

    private static double getX(int idealUnit, int erlangUnit) {
        if (idealUnit <= 0) return 0;
        var pow = valueOf(erlangUnit).pow(idealUnit);
        var numerator = pow.multiply(valueOf(idealUnit));
        var denominator = factorial(idealUnit).multiply(valueOf(idealUnit - erlangUnit));
        return numerator.divide(denominator, 4, RoundingMode.HALF_UP).doubleValue();
    }

    private static double getY(int idealUnit, int erlangUnit) {
        var y = ZERO;
        int i = 0;
        while (i < idealUnit) {
            var pow = valueOf(erlangUnit).pow(i);
            y = y.add(pow.divide(factorial(i++), 4, RoundingMode.HALF_UP));
        }
        return y.doubleValue();
    }

    private static double getServiceLevel(int numberOfCallsPerHour, int avgServiceTimeInMinutes, int answerTimeInSeconds, int idealUnit) {
        var erlangUnit = getErlangUnit(numberOfCallsPerHour, avgServiceTimeInMinutes);
        var aht = avgServiceTimeInMinutes * 60;
        var exponentiation = -(idealUnit - erlangUnit) * (double) answerTimeInSeconds / aht;
        var bigDecimal = valueOf(Math.exp(exponentiation)).setScale(4, RoundingMode.HALF_UP).doubleValue();
        double erlangC = getErlangC(numberOfCallsPerHour, avgServiceTimeInMinutes, idealUnit);
        return 1 - erlangC * bigDecimal;
    }

    private static BigDecimal factorial(int n) {
        var f = ONE;
        int i = 2;
        while (i <= n) f = f.multiply(valueOf(i++));
        return f;
    }

    private static int getRawNumberOfAgents(int numberOfCallsPerHour, int avgServiceTimeInMinutes,
                                            int answerTimeInSeconds, int requiredServiceLevelPercent) {
        var idealUnit = getErlangUnit(numberOfCallsPerHour, avgServiceTimeInMinutes) + 1;
        var serviceLevel = 0;
        while (serviceLevel < requiredServiceLevelPercent) {
            serviceLevel = (int) Math.round(getServiceLevel(numberOfCallsPerHour, avgServiceTimeInMinutes,
                    answerTimeInSeconds, idealUnit) * 100);
            ++idealUnit;
        }
        return idealUnit - 1;
    }

    private static double getAverageSpeedOfAnswer(int numberOfCallsPerHour, int avgServiceTimeInMinutes,
                                                  int answerTimeInSeconds, int requiredServiceLevelPercent) {
        var rawNumberOfAgents = getRawNumberOfAgents(numberOfCallsPerHour, avgServiceTimeInMinutes,
                answerTimeInSeconds, requiredServiceLevelPercent);
        var erlangC = getErlangC(numberOfCallsPerHour, avgServiceTimeInMinutes,
                rawNumberOfAgents);
        var avgServiceTimeInSeconds = avgServiceTimeInMinutes * 60;
        return erlangC * avgServiceTimeInSeconds / (rawNumberOfAgents -
                getErlangUnit(numberOfCallsPerHour, avgServiceTimeInMinutes));
    }

    private static double getImmediateAnswer(int numberOfCallsPerHour, int avgServiceTimeInMinutes,
                                             int answerTimeInSeconds, int requiredServiceLevelPercent) {
        var rawNumberOfAgents = getRawNumberOfAgents(numberOfCallsPerHour, avgServiceTimeInMinutes,
                answerTimeInSeconds, requiredServiceLevelPercent);
        return getErlangC(numberOfCallsPerHour, avgServiceTimeInMinutes, rawNumberOfAgents) * 100;
    }

    private static double getOccupancy(int numberOfCallsPerHour, int avgServiceTimeInMinutes,
                                       int answerTimeInSeconds, int requiredServiceLevelPercent) {
        var erlangUnit = getErlangUnit(numberOfCallsPerHour, avgServiceTimeInMinutes);
        var rawNumberOfAgents = getRawNumberOfAgents(numberOfCallsPerHour, avgServiceTimeInMinutes,
                answerTimeInSeconds, requiredServiceLevelPercent);
        return (double) erlangUnit * 100 / rawNumberOfAgents;
    }

    private static int getAgentsWithShrinkage(int numberOfCallsPerHour, int avgServiceTimeInMinutes,
                                              int answerTimeInSeconds, int requiredServiceLevelPercent, int shrinkage) {
        var rawNumberOfAgents = getRawNumberOfAgents(numberOfCallsPerHour, avgServiceTimeInMinutes,
                answerTimeInSeconds, requiredServiceLevelPercent);
        return (int) Math.ceil(rawNumberOfAgents / (1 - ((double) shrinkage / 100)));
    }

    public static Agents getFullAgents(ErlangData erlangData) {
        var numberOfCallsPerHour = erlangData.numberOfCallsPerHour;
        var avgServiceTimeInMinutes = erlangData.avgServiceTimeInMinutes;
        var answerTimeInSeconds = erlangData.answerTimeInSeconds;
        var requiredServiceLevelPercent = erlangData.requiredServiceLevelPercent;
        var shrinkage = erlangData.shrinkage;
        var rawNumberOfAgents = getRawNumberOfAgents(numberOfCallsPerHour, avgServiceTimeInMinutes,
                answerTimeInSeconds, requiredServiceLevelPercent);
        return Agents.builder()
                .agents(rawNumberOfAgents)
                .withShrinkage(getAgentsWithShrinkage(numberOfCallsPerHour, avgServiceTimeInMinutes,
                        answerTimeInSeconds, requiredServiceLevelPercent, shrinkage))
                .srvLevel((int) Math.round(getServiceLevel(numberOfCallsPerHour, avgServiceTimeInMinutes,
                        answerTimeInSeconds, rawNumberOfAgents) * 100))
                .queued((int) Math.round(getErlangC(numberOfCallsPerHour, avgServiceTimeInMinutes, rawNumberOfAgents) * 100))
                .abndon((int) Math.round(getImmediateAnswer(numberOfCallsPerHour, avgServiceTimeInMinutes,
                        answerTimeInSeconds, requiredServiceLevelPercent)))
                .usage((int) Math.round(getOccupancy(numberOfCallsPerHour, avgServiceTimeInMinutes,
                        answerTimeInSeconds, requiredServiceLevelPercent)))
                .avgAnswers((int) Math.round(getAverageSpeedOfAnswer(numberOfCallsPerHour, avgServiceTimeInMinutes,
                        answerTimeInSeconds, requiredServiceLevelPercent)))
                .build();
    }

    @Data
    @Builder
    public static class ErlangData {
        public int numberOfCallsPerHour;
        public int avgServiceTimeInMinutes;
        public int answerTimeInSeconds;
        public int requiredServiceLevelPercent;
        public int shrinkage;
    }

    @Data
    @Builder
    public static class Agents {
        public int agents;
        public int withShrinkage;
        public int srvLevel;
        public int queued;
        public int abndon;
        public int usage;
        public int avgAnswers;
    }
}


