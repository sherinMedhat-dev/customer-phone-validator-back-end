package eg.jumia.phonevalidator.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Data
@Table(name = "phone_configuration")
@Entity
public class PhoneConfiguration  extends  BaseEntity{

    @Size(max = 50)
    @Column(name = "country_code")
    private String countryCode;
    @Size(max = 100)
    @Column(name = "country_name")
    private String countryName;
    @Size(max = 200)
    @Column(name = "regex_pattern")
    private String regex_pattern;
}
