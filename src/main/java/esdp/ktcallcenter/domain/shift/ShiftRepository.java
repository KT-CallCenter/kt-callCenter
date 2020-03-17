package esdp.ktcallcenter.domain.shift;

import esdp.ktcallcenter.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
//    Set<Shift>findAllById(Integer id);

//    List<Shift>findAllByEmployees(Employee employee);
}
