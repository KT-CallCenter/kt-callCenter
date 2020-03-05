package esdp.ktcallcenter.domain.contract;

import esdp.ktcallcenter.domain.employee.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Data
@Table(name = "contracts")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String name;

    @ManyToMany(mappedBy = "contracts")
    private Set<Employee> employees = new HashSet<>();

}
