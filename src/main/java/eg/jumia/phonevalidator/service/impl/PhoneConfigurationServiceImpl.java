package eg.jumia.phonevalidator.service.impl;

import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import eg.jumia.phonevalidator.repository.PhoneConfigurationRepository;
import eg.jumia.phonevalidator.repository.specification.PhoneConfigFilterSpecification;
import eg.jumia.phonevalidator.service.PhoneConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhoneConfigurationServiceImpl implements PhoneConfigurationService {

    @Autowired
    private PhoneConfigurationRepository phoneConfigurationRepository;


    @Override
    public Map<String, String> getPhoneConfigurationsMap(String countryCode) {
        List<PhoneConfiguration> phoneConfigurationList = phoneConfigurationRepository.findAll(
                PhoneConfigFilterSpecification
                        .equalsCountryCodeOrIgnoreIfNull(countryCode));
        return phoneConfigurationList.stream().collect(
                Collectors.toMap(PhoneConfiguration::getCountryCode, PhoneConfiguration::getRegex_pattern));

    }
}
