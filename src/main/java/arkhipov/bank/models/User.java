package arkhipov.bank.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;

    private String address;

    private int age;

    @OneToMany
    @Transient
    private List<Account> accountList;

    @OneToMany
    @Transient
    private List<Transaction> transactionList;

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
