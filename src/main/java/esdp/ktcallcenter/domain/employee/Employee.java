package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.domain.language.Language;
import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.contract.Contract;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_contract",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "contract_id")}
    )
    private Set<Contract> contracts = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_language",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "language_id")}
    )
    private Set<Language> languages = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

}
