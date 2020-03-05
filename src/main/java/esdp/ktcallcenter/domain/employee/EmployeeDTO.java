package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.shift.Shift;
import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
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

    private Set<Shift> shifts = new HashSet<>();
    private Set<Language> languages= new HashSet<>();
    private Region region;

static EmployeeDTO from(Employee employee){
    return builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .shifts(employee.getShifts())
            .languages(employee.getLanguages())
            .region(employee.getRegion())
            .build();

}

}
