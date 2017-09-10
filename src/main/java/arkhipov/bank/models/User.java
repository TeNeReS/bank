package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;

    private String address;

    private int age;

    @Transient
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Account> accountList;

    @Transient
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
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
