package eg.jumia.phonevalidator.repository;

import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer,Integer> , JpaSpecificationExecutor<Customer> {

    List<Customer> findAllByPhoneStartsWith(String countryCode);

   // List<Customer> findAllByPhoneMatchesRegex(String patternMatch);

}
