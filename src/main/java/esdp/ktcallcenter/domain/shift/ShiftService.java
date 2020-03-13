package esdp.ktcallcenter.domain.shift;

import esdp.ktcallcenter.domain.employee.Employee;
import esdp.ktcallcenter.domain.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShiftService {

    private final EmployeeService employeeService;

    public List<Shift> addEmployeesWithShift(){

        List<Employee> employeesByErlangToFirstShift = employeeService.getEmployeesByErlangToFirstShift();
        Shift build1 = Shift.builder().name("смена 1").id(3).employees(employeesByErlangToFirstShift).build();

        List<Employee> employeesByErlangToSecondShift = employeeService.getEmployeesByErlangToSecondShift(employeesByErlangToFirstShift);
        Shift build2 = Shift.builder().name("смена 2").id(4).employees(employeesByErlangToSecondShift).build();

        List<Shift>shiftArrayList= new ArrayList<>();
        shiftArrayList.add(build1);
        shiftArrayList.add(build2);

        return shiftArrayList;
    };


}
