package esdp.ktcallcenter.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErlangFormula {
    private static final Random random = new Random();

    public static int getCountOfEmployees(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

}

