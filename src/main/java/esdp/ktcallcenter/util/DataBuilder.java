package esdp.ktcallcenter.util;

import esdp.ktcallcenter.domain.shift.Shift;
import esdp.ktcallcenter.domain.employee.Employee;
import esdp.ktcallcenter.domain.employee.EmployeeRepository;
import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.language.LanguageRepository;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.region.RegionRepository;
import esdp.ktcallcenter.domain.shift.ShiftRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Configuration
public class DataBuilder {

    @Bean
    public CommandLineRunner fill(EmployeeRepository employeeRepository,
                                  ShiftRepository shiftRepository,
                                  RegionRepository regionRepository,
                                  LanguageRepository languageRepository) {
        return (args) -> {
            DataReader.getShifts().forEach(shift -> {
                Shift build = Shift.builder().name(shift).build();
                shiftRepository.save(build);
            });

            DataReader.getLanguages().forEach(language -> {
                Language build = Language.builder().name(language).build();
                languageRepository.save(build);
            });

            DataReader.getRegions().forEach(region -> {
                Region build = Region.builder().name(region).build();
                regionRepository.save(build);
            });

            Region regionSouth = regionRepository.findById(1).get();

            DataReader.readEmployeeData().forEach(employee -> {
                Employee build = Employee.builder().firstName(employee.firstName).lastName(employee.lastName).region(regionSouth).build();
                employeeRepository.save(build);
            });


        };
    }
}