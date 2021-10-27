package eg.jumia.phonevalidator.service;

import eg.jumia.phonevalidator.DataGenerator.EntityGenerator;
import eg.jumia.phonevalidator.Utils.Constants;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
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

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneConfigurationServiceTest {

    @Autowired
    private PhoneConfigurationRepository phoneConfigurationRepository;
    @Autowired
    private PhoneConfigurationService phoneConfigurationService;


    @Autowired
    private EntityGenerator entityGenerator;

    List<PhoneConfiguration> phoneConfigurationList;

    @BeforeEach
    public void setUp() {
        phoneConfigurationList = (List<PhoneConfiguration>) phoneConfigurationRepository
                .saveAll(entityGenerator.generatePhoneConfigurations());
    }

    @AfterEach
    public void tearDown() {
        phoneConfigurationRepository.deleteAll(phoneConfigurationList);
    }

    @Test()
    void findPhoneConfigurationFailedToFindTest() {
        Map<String, String> phoneConfigurationMap = phoneConfigurationService.getPhoneConfigurationsMap(Constants.PHONE_CONFIG_COUNTRY_CODE_NOT_EXISTS);
        Assert.assertEquals(0, phoneConfigurationMap.size());
        Assert.assertTrue(phoneConfigurationMap.get(Constants.PHONE_CONFIG_COUNTRY_CODE_NOT_EXISTS) == null);

    }

    @Test()
    void findPhoneConfigurationWithKeyMatchTest() {
        Map<String, String> phoneConfigurationMap = phoneConfigurationService.getPhoneConfigurationsMap(Constants.PHONE_CONFIG_COUNTRY_CODE);
        Assert.assertEquals(1, phoneConfigurationMap.size());
        Assert.assertTrue(phoneConfigurationMap.get(Constants.PHONE_CONFIG_COUNTRY_CODE_NOT_EXISTS) == null);
        Assert.assertEquals(Constants.PHONE_CONFIG_PATTERN, phoneConfigurationMap.get(Constants.PHONE_CONFIG_COUNTRY_CODE));

    }

}
