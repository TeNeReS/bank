package arkhipov.bank.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String address;
    private int age;

    @OneToMany(mappedBy = "user")
    private List<Account> accountList;

    public User() {
    }

    public User(Integer id, String name, String address, int age) {
        super(id);
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name=" + name +
                ", address=" + address +
                ", age=" + age +
                '}';
    }
}
