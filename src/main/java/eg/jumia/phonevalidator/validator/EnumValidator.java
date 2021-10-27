package eg.jumia.phonevalidator.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<EnumValidatorAnnotation, Enum> {

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return !value.toString().equals("null");
    }
}
