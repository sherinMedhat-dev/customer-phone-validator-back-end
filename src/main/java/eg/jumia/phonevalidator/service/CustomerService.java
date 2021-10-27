package eg.jumia.phonevalidator.service;

import eg.jumia.phonevalidator.model.dto.PhoneDetailsDTO;
import eg.jumia.phonevalidator.model.dto.PhoneFilterDTO;

import java.util.List;

public interface CustomerService {

    List<PhoneDetailsDTO> filterCustomerPhone(PhoneFilterDTO phoneFilterDTO);
}
