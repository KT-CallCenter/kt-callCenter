package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.shift.Shift;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "employees")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String firstName;

    @NotBlank
    @Column
    private String lastName;

    @Column
    private Shift shift;

    @Column
    private Language language;

    @Column
    private Region region;

}
