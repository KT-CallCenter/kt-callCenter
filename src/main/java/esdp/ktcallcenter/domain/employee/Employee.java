package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.shift.Shift;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
    @NotEmpty
    @Column
    private String firstName;

    @NotBlank
    @NotEmpty
    @Column
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employee")
    private List<Shift> shifts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employee")
    private List<Language> languages= new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Region region;

}
