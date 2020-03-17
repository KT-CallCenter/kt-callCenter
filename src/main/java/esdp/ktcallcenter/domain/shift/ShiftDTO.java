package esdp.ktcallcenter.domain.shift;

import esdp.ktcallcenter.domain.employee.Employee;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class ShiftDTO {

        private Integer id;
        @NotBlank
        private String name;

        private List<Employee> employees;

        static ShiftDTO from(Shift shift) {
            return builder()
                    .id(shift.getId())
                    .name(shift.getName())
                    .employees(shift.getEmployees())
                    .build();

        }
}
