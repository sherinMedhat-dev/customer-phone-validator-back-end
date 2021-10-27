package eg.jumia.phonevalidator;

import eg.jumia.phonevalidator.exceptionHandler.ExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ExceptionHandler.class)
public class PhoneValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneValidatorApplication.class, args);
    }

}
