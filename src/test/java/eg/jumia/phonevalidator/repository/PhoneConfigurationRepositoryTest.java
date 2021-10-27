package eg.jumia.phonevalidator.repository;

import eg.jumia.phonevalidator.DataGenerator.EntityGenerator;
import eg.jumia.phonevalidator.Utils.Constants;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import eg.jumia.phonevalidator.repository.specification.PhoneConfigFilterSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneConfigurationRepositoryTest {

    @Autowired
    private PhoneConfigurationRepository phoneConfigurationRepository;
    @Autowired
    private  EntityGenerator entityGenerator;

    List<PhoneConfiguration> phoneConfigurationList;
    @BeforeEach
    public void setUp() {
        phoneConfigurationList = (List<PhoneConfiguration>) phoneConfigurationRepository.saveAll(entityGenerator.generatePhoneConfigurations());
    }

    @AfterEach
    public void tearDown() {
        phoneConfigurationRepository.deleteAll(phoneConfigurationList);
    }

    @Test
    void findByCountryCodeTest() {
       List<PhoneConfiguration> phoneConfigurationListActual =phoneConfigurationRepository.findAll(
               PhoneConfigFilterSpecification
                       .equalsCountryCodeOrIgnoreIfNull(Constants.PHONE_CONFIG_COUNTRY_CODE));
        Assert.assertTrue(phoneConfigurationListActual.stream().count()==1);
       Assert.assertEquals(Constants.PHONE_CONFIG_PATTERN,phoneConfigurationListActual.get(0).getRegex_pattern());
    }
    @Test
    void findByCountryCodeNotFoundPatternTest() {
        List<PhoneConfiguration> phoneConfigurationListActual =
                phoneConfigurationRepository.findAll(
                        PhoneConfigFilterSpecification
                                .equalsCountryCodeOrIgnoreIfNull(Constants.PHONE_CONFIG_COUNTRY_CODE_NOT_EXISTS));

        Assert.assertTrue(phoneConfigurationListActual.stream().count()==0);
    }
    @Test
    void findAllPhoneConfigurationsTest() {
        List<PhoneConfiguration> phoneConfigurationListActual =
                phoneConfigurationRepository.findAll(
                        PhoneConfigFilterSpecification
                                .equalsCountryCodeOrIgnoreIfNull(null));

        Assert.assertEquals( phoneConfigurationList.stream().count(),phoneConfigurationListActual.stream().count());
    }
}
