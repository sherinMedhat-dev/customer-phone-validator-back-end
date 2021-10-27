package eg.jumia.phonevalidator.mapper;

import eg.jumia.phonevalidator.model.dto.PhoneDetailsDTO;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import eg.jumia.phonevalidator.validator.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Component
public class PhoneDetailsMapper {

    @Autowired
    private PhoneValidator phoneValidator;

    public List<PhoneDetailsDTO> mapToPhoneDetails(List<Customer> customers, Map<String, String> countryCodePatternMatcher) {

        return Optional.ofNullable(customers).
                orElseGet(Collections::emptyList).stream()
                .map(c -> extractPhoneDetail(c, countryCodePatternMatcher))
                .collect(Collectors.toList());
    }

    private PhoneDetailsDTO extractPhoneDetail(Customer customer, Map<String, String> countryCodePatternMatcher) {
        PhoneDetailsDTO phoneDetailsDTO = new PhoneDetailsDTO(customer);
        phoneDetailsDTO.setCountryCode(extractCountryCode(customer.getPhone()));
        phoneDetailsDTO.setState(extractStatusByPatternMatch(phoneDetailsDTO, countryCodePatternMatcher));
        return phoneDetailsDTO;
    }

    private String extractCountryCode(String phoneNumber) {
        try {
            return StringUtils.hasText(phoneNumber) ?
                    phoneNumber.substring(phoneNumber.indexOf("(") + 1,
                            phoneNumber.indexOf(")")) : "";
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    private PhoneValidationState extractStatusByPatternMatch(PhoneDetailsDTO phoneDetailsDTO, Map<String, String> countryCodePatternMatcher) {

        String regex = StringUtils.hasText(phoneDetailsDTO.getCountryCode())?
                countryCodePatternMatcher.get(phoneDetailsDTO.getCountryCode()):null;
        return phoneValidator.isPhoneNumberValidForCountry(regex, phoneDetailsDTO.getPhoneNumber());
    }

}
