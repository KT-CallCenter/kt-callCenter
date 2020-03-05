package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.shift.Shift;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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

    private List<Shift> shifts = new ArrayList<>();
    private List<Language> languages= new ArrayList<>();
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
