package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.contract.Contract;
import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class EmployeeDTO {

    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    private Set<Contract> contracts = new HashSet<>();
    private Set<Language> languages= new HashSet<>();
    private Region region;

static EmployeeDTO from(Employee employee){
    return builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .contracts(employee.getContracts())
            .languages(employee.getLanguages())
            .region(employee.getRegion())
            .build();

}

}
