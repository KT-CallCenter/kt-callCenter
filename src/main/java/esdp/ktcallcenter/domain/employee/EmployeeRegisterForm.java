package esdp.ktcallcenter.domain.employee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmployeeRegisterForm {
    @NotBlank
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Should contain only letters")
    private String firstName = "";

    @NotBlank
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Should contain only letters")
    private String lastName = "";


}

