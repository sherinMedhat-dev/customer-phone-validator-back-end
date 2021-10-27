package eg.jumia.phonevalidator.service;

import java.util.Map;

public interface PhoneConfigurationService {

    Map<String, String> getPhoneConfigurationsMap(String countryCode);

}
