package esdp.ktcallcenter.domain.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmployeeAlreadyExistException extends RuntimeException {

    public EmployeeAlreadyExistException() {
        super();
    }

    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
