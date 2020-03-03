package esdp.ktcallcenter.domain.shift;

import esdp.ktcallcenter.domain.employee.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Table(name = "shifts")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Employee employee;

}
