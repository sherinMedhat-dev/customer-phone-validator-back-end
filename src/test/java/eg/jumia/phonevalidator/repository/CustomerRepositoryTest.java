package eg.jumia.phonevalidator.repository;

import eg.jumia.phonevalidator.DataGenerator.EntityGenerator;
import eg.jumia.phonevalidator.Utils.Constants;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import eg.jumia.phonevalidator.repository.specification.CustomerFilterSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private PhoneConfigurationRepository phoneConfigurationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private  EntityGenerator entityGenerator;

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
    @Test
    void findCustomerPhoneByCountryCodeTest() {

         //List<Customer> phoneConfigurationOptionalActual = customerRepository.findAllByPhoneStartsWith("("+Constants.PHONE_CONFIG_COUNTRY_CODE+ ")");
        List<Customer> phoneConfigurationOptionalActual= customerRepository.findAll(
                CustomerFilterSpecification.matchesCountryCodeOrIgnoreIfNull(Constants.PHONE_CONFIG_COUNTRY_CODE));
         Assert.assertTrue(phoneConfigurationOptionalActual.stream().count()==1 );
       Customer actualCustomer =phoneConfigurationOptionalActual.get(0);
       Assert.assertEquals(Constants.CUSTOMER_PHONE_CORRECT, actualCustomer.getPhone());
    }
    @Test
    void findCustomerPhoneWithNoFilterTest() {

        List<Customer> phoneConfigurationOptionalActual = customerRepository.findAll(
                CustomerFilterSpecification.matchesCountryCodeOrIgnoreIfNull(null));
        Assert.assertEquals(customerList.stream().count(), phoneConfigurationOptionalActual.stream().count());
    }


}
