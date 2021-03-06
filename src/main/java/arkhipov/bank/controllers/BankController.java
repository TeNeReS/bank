package arkhipov.bank.controllers;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Person;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.AccountRepository;
import arkhipov.bank.repositories.PersonRepository;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
public class BankController {
    private PersonRepository personRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public BankController(PersonRepository personRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("persons")
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @PostMapping("persons")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("persons/{id}/accounts")
    public List<Account> getAccountsByPersonId(@PathVariable(value = "id") int personId) {
        return accountRepository.getAll(personId);
    }

    @PostMapping("persons/{id}/accounts")
    public Account createAccount(@RequestBody Account account, @PathVariable(value = "id") int personId) {
        return accountRepository.create(account, personId);
    }

    @GetMapping("transactions")
    public Page<Transaction> getAllTransactions(Pageable pageable, @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate, @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        if (startDate != null || endDate != null)
            return transactionRepository.getBetween(startDate, endDate, pageable);
        else return transactionRepository.getAll(pageable);
    }

    @PostMapping("transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.execute(transaction);
    }
}