package esdp.ktcallcenter.domain.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
private final EmployeeRepository employeeRepository;


    public void addEmployee(String firstName, String lastName) {
        Employee employee = Employee.builder().firstName(firstName).lastName(lastName).build();
        employeeRepository.save(employee);
    }
}
