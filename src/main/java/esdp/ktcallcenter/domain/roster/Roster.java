package esdp.ktcallcenter.domain.roster;

import esdp.ktcallcenter.domain.employee.Employee;
import esdp.ktcallcenter.domain.shiftTime.ShiftTime;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Table(name = "rosters")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)

public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private ShiftTime shiftTime;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    @Column
    private Date date;
}
