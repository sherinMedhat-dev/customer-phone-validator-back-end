package eg.jumia.phonevalidator.controller;

import eg.jumia.phonevalidator.model.dto.PhoneDetailsDTO;
import eg.jumia.phonevalidator.model.dto.PhoneFilterDTO;
import eg.jumia.phonevalidator.model.entity.Customer;
import eg.jumia.phonevalidator.model.enums.PhoneValidationState;
import eg.jumia.phonevalidator.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@Validated
@RequestMapping("/api/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/filter")
    @ApiOperation(value = "filter customer phone number", notes = "filter customer phone number ")
    public ResponseEntity<List<PhoneDetailsDTO>> filterCustomerPhones(@RequestBody @Valid PhoneFilterDTO phoneFilterDTO) {
        return ResponseEntity.ok( customerService.filterCustomerPhone(phoneFilterDTO));
    }
    @GetMapping(path = "/phoneValidationState")
    @ApiOperation(value = "Get validation States")
    public ResponseEntity<PhoneValidationState[]> getPhoneValidationStates() {
        return ResponseEntity.ok(PhoneValidationState.values());
    }
}
