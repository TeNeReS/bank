package arkhipov.bank.beans;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Person;
import arkhipov.bank.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class PersonBean implements Serializable{
    private PersonRepository personRepository;

    private String name;

    private String address;

    private Integer age;

    private long total;

    @Autowired
    public PersonBean(PersonRepository personRepository) {
        this.personRepository = personRepository;
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

    public List<Person> getAll() {
        return (List<Person>) personRepository.findAll();
    }

    public void create(){
        Person newPerson = new Person(name, address, age);
        personRepository.save(newPerson);
        clearForm();
    }

    public Person getWithAccounts(int id) {
        Person person = personRepository.getWithAccounts(id);
        long sum = 0;
        for (Account account : person.getAccountList()) {
            sum += account.getAmount();
        }
        this.total = sum;
        return person;
    }

    private void clearForm(){
        this.name = null;
        this.address = null;
        this.age = null;
    }
}
