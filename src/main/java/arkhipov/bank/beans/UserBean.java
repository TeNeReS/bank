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

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public void create(){
        User newUser = new User(name, address, age);
        userRepository.save(newUser);
        clearForm();
    }

    public User getWithAccounts(int id) {
        User user = userRepository.getWithAccounts(id);
        long sum = 0;
        for (Account account : user.getAccountList()) {
            sum += account.getAmount();
        }
        this.total = sum;
        return user;
    }

    private void clearForm(){
        this.name = null;
        this.address = null;
        this.age = null;
    }
}
