package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Person extends BaseEntity {
    private String name;
    private String address;
    private int age;

    @JsonIgnoreProperties(value = "person", allowSetters = true)
    @OneToMany(mappedBy = "person")
    private List<Account> accountList;

    public Person(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
