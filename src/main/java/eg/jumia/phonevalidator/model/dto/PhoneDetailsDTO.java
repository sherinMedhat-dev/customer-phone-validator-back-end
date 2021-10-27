package eg.jumia.phonevalidator.model.dto;

import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
public class PhoneDetailsDTO {
    private String CountryCode;
    @Enumerated(EnumType.STRING)
    private PhoneValidationState State;
    private String PhoneNumber;

    public PhoneDetailsDTO(Customer customer){
        PhoneNumber=customer.getPhone();
    }

}
