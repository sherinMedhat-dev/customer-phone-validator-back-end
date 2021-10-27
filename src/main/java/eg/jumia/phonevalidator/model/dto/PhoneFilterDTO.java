package eg.jumia.phonevalidator.model.dto;

import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class PhoneFilterDTO {
   private String CountryCode;
   @Enumerated(EnumType.STRING)
   private PhoneValidationState State;
}
