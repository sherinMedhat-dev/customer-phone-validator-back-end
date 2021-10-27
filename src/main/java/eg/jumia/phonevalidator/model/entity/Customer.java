package eg.jumia.phonevalidator.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Table(name = "customer")
@Entity
public class Customer extends BaseEntity {

    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "phone")
    private String phone;
}
