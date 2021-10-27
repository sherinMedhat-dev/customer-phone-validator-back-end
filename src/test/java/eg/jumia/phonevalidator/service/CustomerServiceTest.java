package eg.jumia.phonevalidator.service;

import eg.jumia.phonevalidator.DataGenerator.EntityGenerator;
import eg.jumia.phonevalidator.Utils.Constants;
import eg.jumia.phonevalidator.model.dto.PhoneDetailsDTO;
import eg.jumia.phonevalidator.model.dto.PhoneFilterDTO;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import eg.jumia.phonevalidator.repository.CustomerRepository;
import eg.jumia.phonevalidator.repository.PhoneConfigurationRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private PhoneConfigurationRepository phoneConfigurationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private  CustomerService customerService;
    @Autowired
    private EntityGenerator entityGenerator;

    List<PhoneConfiguration> phoneConfigurationList;
    List<Customer> customerList;
    @BeforeEach
    public void setUp() {
        phoneConfigurationList = (List<PhoneConfiguration>) phoneConfigurationRepository.saveAll(entityGenerator.generatePhoneConfigurations());
        customerList = (List<Customer>) customerRepository.saveAll(entityGenerator.generateCustomers());
    }

    @AfterEach
    public void tearDown() {
        phoneConfigurationRepository.deleteAll(phoneConfigurationList);
        customerRepository.deleteAll(customerList);
    }
    @Test()
    void filterPhoneNumberByCodeFoundTest() {
        PhoneFilterDTO phoneFilterDTO =new PhoneFilterDTO();
        phoneFilterDTO.setCountryCode(Constants.PHONE_CONFIG_COUNTRY_CODE);
        List<PhoneDetailsDTO> phoneDetailsDTOS=customerService.filterCustomerPhone(phoneFilterDTO);
        Assert.assertEquals(1,phoneDetailsDTOS.size());
        Assert.assertTrue( phoneDetailsDTOS.stream().allMatch(m->  Constants.PHONE_CONFIG_COUNTRY_CODE .equals(m.getCountryCode())));
        Assert.assertEquals(Constants.CUSTOMER_PHONE_CORRECT,phoneDetailsDTOS.stream().findFirst().get().getPhoneNumber());
        Assert.assertEquals(PhoneValidationState.Valid ,phoneDetailsDTOS.stream().findFirst().get().getState());
    }
    @Test()
    void filterPhoneNumberByValidStatusFoundTest() {
        PhoneFilterDTO phoneFilterDTO =new PhoneFilterDTO();
        phoneFilterDTO.setState(PhoneValidationState.Valid);
        List<PhoneDetailsDTO> phoneDetailsDTOS=customerService.filterCustomerPhone(phoneFilterDTO);
        Assert.assertEquals(1,phoneDetailsDTOS.size());
        Assert.assertTrue( phoneDetailsDTOS.stream().allMatch(m->  PhoneValidationState.Valid.equals(m.getState())));
        Assert.assertEquals(Constants.CUSTOMER_PHONE_CORRECT,phoneDetailsDTOS.stream().findFirst().get().getPhoneNumber());
        Assert.assertEquals(PhoneValidationState.Valid ,phoneDetailsDTOS.stream().findFirst().get().getState());
    }
    @Test()
    void filterPhoneNumberHavingWrongPatternTest() {
        phoneConfigurationList.add(phoneConfigurationRepository.save(
                entityGenerator.generatePhoneConfiguration(Constants.PHONE_CONFIG_COUNTRY_CODE_WRONG_PATTERN,
                        Constants.PHONE_CONFIG_PATTERN_WRONG_PATTERN)));
        customerList.add(customerRepository.save(entityGenerator.generateCustomer(Constants.CUSTOMER_PHONE_WRONG_PATTERN)));

        PhoneFilterDTO phoneFilterDTO =new PhoneFilterDTO();
        phoneFilterDTO.setCountryCode(Constants.PHONE_CONFIG_COUNTRY_CODE_WRONG_PATTERN);
        List<PhoneDetailsDTO> phoneDetailsDTOS= customerService.filterCustomerPhone(phoneFilterDTO);

        Assert.assertEquals(1,phoneDetailsDTOS.size());
        Assert.assertTrue( phoneDetailsDTOS.stream().allMatch(m->  PhoneValidationState.Undetermined.equals(m.getState())));
        Assert.assertEquals(Constants.CUSTOMER_PHONE_WRONG_PATTERN,phoneDetailsDTOS.stream().findFirst().get().getPhoneNumber());
        Assert.assertEquals(PhoneValidationState.Undetermined,phoneDetailsDTOS.stream().findFirst().get().getState());
    }
    @Test()
    void filterPhoneNumberHavingInValidStatusTest() {

        customerList.add(customerRepository.save(entityGenerator.generateCustomer(Constants.CUSTOMER_PHONE_INVALID)));

        PhoneFilterDTO phoneFilterDTO =new PhoneFilterDTO();
        phoneFilterDTO.setCountryCode(Constants.PHONE_CONFIG_COUNTRY_CODE_2);
        phoneFilterDTO.setState(PhoneValidationState.InValid);
        List<PhoneDetailsDTO> phoneDetailsDTOS= customerService.filterCustomerPhone(phoneFilterDTO);

        Assert.assertEquals(1,phoneDetailsDTOS.size());
        Assert.assertTrue( phoneDetailsDTOS.stream().allMatch(m->  PhoneValidationState.InValid.equals(m.getState())));
        Assert.assertEquals(Constants.CUSTOMER_PHONE_INVALID,phoneDetailsDTOS.stream().findFirst().get().getPhoneNumber());
        Assert.assertEquals(PhoneValidationState.InValid,phoneDetailsDTOS.stream().findFirst().get().getState());
    }
}
