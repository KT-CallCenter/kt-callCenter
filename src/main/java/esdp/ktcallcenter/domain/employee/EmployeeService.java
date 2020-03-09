package esdp.ktcallcenter.domain.employee;

import esdp.ktcallcenter.util.ErlangFormula;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


    public void addEmployee(String firstName, String lastName) {
        if (employeeRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new EmployeeAlreadyExistException();
        }
        Employee employee = Employee.builder().firstName(firstName).lastName(lastName).build();
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByErlangToFirstShift() {
        List<Employee> allEmployees = employeeRepository.findAll();
        int countOfEmployees = ErlangFormula.getCountOfEmployees(1, 10);
        return allEmployees.stream()
                .limit(countOfEmployees)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByErlangToSecondShift(List<Employee> firstShiftEmployees) {
        List<Employee> allEmployees = employeeRepository.findAll();
        int countOfEmployees = ErlangFormula.getCountOfEmployees(1, 10);
        allEmployees.removeAll(firstShiftEmployees);
        return allEmployees.stream()
                .limit(countOfEmployees)
                .collect(Collectors.toList());
    }
}
