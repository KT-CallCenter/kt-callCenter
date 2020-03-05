package esdp.ktcallcenter.util;

import esdp.ktcallcenter.domain.contract.Contract;
import esdp.ktcallcenter.domain.employee.Employee;
import esdp.ktcallcenter.domain.employee.EmployeeRepository;
import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.language.LanguageRepository;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.region.RegionRepository;
import esdp.ktcallcenter.domain.contract.ContractRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;


@Configuration
public class DataBuilder {

    @Bean
    public CommandLineRunner fill(EmployeeRepository employeeRepository,
                                  ContractRepository contractRepository,
                                  RegionRepository regionRepository,
                                  LanguageRepository languageRepository) {
        return (args) -> {
//доделать заполнение базы
            DataReader.getContracts().forEach(contract -> {
                Contract build = Contract.builder().name(contract).build();
                contractRepository.save(build);
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
