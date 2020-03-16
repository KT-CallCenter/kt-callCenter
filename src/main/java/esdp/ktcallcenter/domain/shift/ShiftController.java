package esdp.ktcallcenter.domain.shift;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor

public class ShiftController {
private final ShiftService shiftService;
    @GetMapping("/generator")
    public String shift(Model model) {
        List<ShiftDTO> shifts = shiftService.addEmployeesWithShift();
        model.addAttribute("shifts", shifts);
        return "index";
    }

}
