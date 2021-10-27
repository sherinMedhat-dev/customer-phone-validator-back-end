package eg.jumia.phonevalidator.DataGenerator;

import eg.jumia.phonevalidator.Utils.Constants;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class EntityGenerator {


    public List<PhoneConfiguration> generatePhoneConfigurations(){
      return  Arrays.asList(
              generatePhoneConfiguration(Constants.PHONE_CONFIG_COUNTRY_CODE,Constants.PHONE_CONFIG_PATTERN),
              generatePhoneConfiguration(Constants.PHONE_CONFIG_COUNTRY_CODE_2,Constants.PHONE_CONFIG_PATTERN_2));
    }
    public PhoneConfiguration generatePhoneConfiguration(String countryCode, String pattern) {
        PhoneConfiguration phoneConfiguration = new PhoneConfiguration();
        phoneConfiguration.setCountryCode(countryCode);
        phoneConfiguration.setRegex_pattern(pattern);
        return phoneConfiguration;
    }
    public Customer generateCustomer(String phoneNumber) {
        Customer customer = new Customer();
        customer.setPhone(phoneNumber);
        customer.setName("test");
        return customer;
    }
    public List<Customer> generateCustomers(){
        return  Arrays.asList(generateCustomer(Constants.CUSTOMER_PHONE_WRONG_CODE),
                generateCustomer(Constants.CUSTOMER_PHONE_WRONG_FORMAT) ,
                generateCustomer(Constants.CUSTOMER_PHONE_WRONG_SPECIAL_CHAR),
                generateCustomer(Constants.CUSTOMER_PHONE_CORRECT)
        );
    }
}
