package arkhipov.bank.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String address;
    private int age;

    @OneToMany(mappedBy = "user")
    private List<Account> accountList;

    public User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
