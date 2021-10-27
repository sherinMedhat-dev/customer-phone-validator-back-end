package eg.jumia.phonevalidator.service.impl;

import eg.jumia.phonevalidator.model.dto.PhoneDetailsDTO;
import eg.jumia.phonevalidator.model.dto.PhoneFilterDTO;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import eg.jumia.phonevalidator.repository.CustomerRepository;
import eg.jumia.phonevalidator.repository.specification.CustomerFilterSpecification;
import eg.jumia.phonevalidator.service.CustomerService;
import eg.jumia.phonevalidator.service.PhoneConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PhoneConfigurationService phoneConfigurationService;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<PhoneDetailsDTO> filterCustomerPhone(PhoneFilterDTO phoneFilterDTO) {
        List<Customer> allCustomersMatchFilters = customerRepository.findAll(
                CustomerFilterSpecification
                        .matchesCountryCodeOrIgnoreIfNull(phoneFilterDTO.getCountryCode()));


        List<PhoneDetailsDTO> phoneDetails = mapToPhoneDetails(allCustomersMatchFilters,
                phoneConfigurationService.getPhoneConfigurationsMap(phoneFilterDTO.getCountryCode()));
        //map to status by using pattern match
        //filter by status if status exists
        return filterByStatus(phoneDetails, phoneFilterDTO.getState());
    }

    private List<PhoneDetailsDTO> filterByStatus(List<PhoneDetailsDTO> phoneDetails, PhoneValidationState phoneValidationState) {
        if (Optional.ofNullable(phoneValidationState).isEmpty()) {
            return phoneDetails;
        }
        return phoneDetails.stream()
                .filter(s -> s.getState() == phoneValidationState).collect(Collectors.toList());
    }

    private List<PhoneDetailsDTO> mapToPhoneDetails(List<Customer> customers, Map<String, String> countryCodePatternMatcher) {

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

    private PhoneValidationState extractStatusByPatternMatch(PhoneDetailsDTO phoneDetailsDTO, Map<String, String> countryCodePatternMatcher) {

            String regex = StringUtils.hasText(phoneDetailsDTO.getCountryCode())?
        countryCodePatternMatcher.get(phoneDetailsDTO.getCountryCode()):null;
            return isPhoneNumberValidForCountry(regex, phoneDetailsDTO.getPhoneNumber());
    }

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
}
