package esdp.ktcallcenter.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataReader {
    private static List<String> contracts;
    private static List<String> languages;
    private static List<String> regions;
    private static List<EmployeeData> employees;

    public static List<String> getContracts() {
        return readStrings(Paths.get("data", "list-shifts"));
    }

    public static List<String> getLanguages() {
        return readStrings(Paths.get("data", "list-languages"));
    }

    public static List<String> getRegions() {
        return readStrings(Paths.get("data", "list-regions"));
    }

    private static List<String> readStrings(Path filePath) {
        try (var lines = Files.lines(filePath)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<EmployeeData> readEmployeeData() {
        var lines = readStrings(Paths.get("data","list-employee"));
        return lines.stream()
                .map(DataReader::toEmployee)
                .collect(Collectors.toList());
    }

    private static EmployeeData toEmployee(String fromString) {
        var parts = fromString.split(" ");
        return EmployeeData.builder()
                .firstName(parts[0])
                .lastName(parts[1])
                .build();
    }

    @Data
    @Builder
    public static class EmployeeData {
        public String firstName;
        public String lastName;
    }
}
