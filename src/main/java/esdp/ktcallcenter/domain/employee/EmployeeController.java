package esdp.ktcallcenter.domain.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String addEmployee(){
        return "addEmployee";
    }


    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeRegisterForm employee,
                              BindingResult validationResult,
                              RedirectAttributes attributes){
        attributes.addFlashAttribute("dto", employee);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/employees";
        }
        try {
            employeeService.addEmployee(employee.getFirstName(),employee.getLastName());
        }catch (EmployeeAlreadyExistException e){
            String message = "Такой оператор с такими данными есть";
//            e.getMessage(message);
            attributes.addFlashAttribute("err", message);
            return "redirect:/employees";
        }

        return "redirect:/employees";
    }

}
