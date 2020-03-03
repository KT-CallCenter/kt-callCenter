package esdp.ktcallcenter.domain.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String addEmployee(){
        return "addEmployee";
    }


    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam String firstName,@RequestParam String lastName){
        employeeService.addEmployee(firstName,lastName);
        return "redirect:/employees";
    }


}
