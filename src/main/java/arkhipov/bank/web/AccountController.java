package arkhipov.bank.web;

import arkhipov.bank.models.Account;
import arkhipov.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private AccountRepository repository;

    @Autowired
    public AccountController( AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public List<Account> getAll(@PathVariable("id") int id) {
        return (List<Account>) repository.getAll(id);
    }


}
