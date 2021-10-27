package eg.jumia.phonevalidator.repository.specification;

import de.cronn.reflection.util.PropertyUtils;
import eg.jumia.phonevalidator.model.entity.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CustomerFilterSpecification {
    public static Specification<Customer> matchesCountryCodeOrIgnoreIfNull(String countryCode) {
        return (root, query, cb) -> countryCode == null ? null : cb.like(root.get("phone"),"("+ countryCode +")%");

       /* return (root, query, cb) -> {
            if (!StringUtils.hasText(countryCode)) return null;
            //Predicate contentFilter = cb.conjunction();
            String phoneFieldName = PropertyUtils.getPropertyName(Customer.class, Customer::getPhone);
            return cb.like(root.get(phoneFieldName), "(" + countryCode + ")%");
        };*/

    }

}
