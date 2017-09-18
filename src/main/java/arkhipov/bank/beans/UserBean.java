package arkhipov.bank.beans;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.User;
import arkhipov.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class UserBean implements Serializable{
    private UserRepository userRepository;

    private String name;

    private String address;

    private Integer age;

    private long total;

    private List<User> filteredUser;

    @Autowired
    public UserBean(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<User> getFilteredUser() {
        return filteredUser;
    }

    public void setFilteredUser(List<User> filteredUser) {
        this.filteredUser = filteredUser;
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public void create(){
        System.out.println("----------------------------------------------------------");
        System.out.println(getName() + getAddress() + getAge());
        User created = new User(null, getName(), getAddress(), getAge());
        System.out.println(created);
        userRepository.save(created);
        clearForm();
    }

    public User getWithAccounts(int id) {
        User user = userRepository.getWithAccounts(id);
        long sum = 0;
        for (Account account : user.getAccountList()) {
            sum += account.getAmount();
        }
        setTotal(sum);
        return user;
    }

    private void clearForm(){
        setName(null);
        setAddress(null);
        setAge(null);
    }
}
