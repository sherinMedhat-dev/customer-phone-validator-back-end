package eg.jumia.phonevalidator.service.impl;

import eg.jumia.phonevalidator.mapper.PhoneDetailsMapper;
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
    @Autowired
    private PhoneDetailsMapper phoneDetailsMapper ;

    @Override
    public List<PhoneDetailsDTO> filterCustomerPhone(PhoneFilterDTO phoneFilterDTO) {
        List<Customer> allCustomersMatchFilters = customerRepository.findAll(
                CustomerFilterSpecification
                        .matchesCountryCodeOrIgnoreIfNull(phoneFilterDTO.getCountryCode()));


        List<PhoneDetailsDTO> phoneDetails = phoneDetailsMapper.mapToPhoneDetails(allCustomersMatchFilters,
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
}
