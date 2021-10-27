package eg.jumia.phonevalidator.repository.specification;

import de.cronn.reflection.util.PropertyUtils;
import eg.jumia.phonevalidator.model.entity.PhoneConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class PhoneConfigFilterSpecification {
    public static Specification<PhoneConfiguration> equalsCountryCodeOrIgnoreIfNull(String countryCode) {

        return (root, query, cb) -> {
            if (!StringUtils.hasText(countryCode)) return null;
            //Predicate contentFilter = cb.conjunction();
            String countryCodeFieldName = PropertyUtils.getPropertyName(PhoneConfiguration.class, PhoneConfiguration::getCountryCode);
            return cb.equal(root.get(countryCodeFieldName), countryCode);
        };

    }

}
