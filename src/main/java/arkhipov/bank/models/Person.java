package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Person extends BaseEntity {
    private String name;
    private String address;
    private int age;

    @OneToMany(mappedBy = "person")
    private List<Account> accountList;

    public Person(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
