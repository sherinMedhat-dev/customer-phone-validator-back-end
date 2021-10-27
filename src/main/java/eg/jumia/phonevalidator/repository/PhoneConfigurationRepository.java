package eg.jumia.phonevalidator.repository;

import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneConfigurationRepository extends BaseRepository<PhoneConfiguration, Integer>, JpaSpecificationExecutor<PhoneConfiguration> {

}
