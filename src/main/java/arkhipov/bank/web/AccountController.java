package arkhipov.bank.web;

import arkhipov.bank.models.Account;
import arkhipov.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts")
    public List<Account> getAll(@PathVariable("id") int id) {
        return repository.getAll(id);
    }

    @PostMapping(value = ("/accounts"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account create(@RequestBody Account account) {
        return repository.save(account);
    }
}
