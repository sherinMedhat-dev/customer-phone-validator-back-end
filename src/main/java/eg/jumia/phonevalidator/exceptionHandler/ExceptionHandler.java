package eg.jumia.phonevalidator.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(HttpServletRequest request, Exception ex) {
        return   new ResponseEntity<Object>(
                "Aproblem occured due to: " + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler({HttpMessageNotReadableException.class, InvalidFormatException.class})
    public ResponseEntity<?> handleFormatsException(HttpServletRequest request, Exception ex) {
        return   new ResponseEntity<Object>(
                "hayy you entered in correct value check message for details : " + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        return   new ResponseEntity<Object>(
                "Aproblem occured due to: " + ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
