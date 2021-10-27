package eg.jumia.phonevalidator.validator;

import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.PatternSyntaxException;


@Component
public class PhoneValidator {
    public PhoneValidationState isPhoneNumberValidForCountry(String regex, String phoneNumber) {
        try {
            if (!Optional.ofNullable(regex).isEmpty() && !Optional.ofNullable(phoneNumber).isEmpty()) {
                boolean isValid = phoneNumber.matches(regex);
                return (isValid) ? PhoneValidationState.Valid : PhoneValidationState.InValid;
            }
        } catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }
        return PhoneValidationState.Undetermined;
    }

}
