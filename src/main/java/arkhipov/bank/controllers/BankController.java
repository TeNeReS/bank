package arkhipov.bank.controllers;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Person;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.AccountRepository;
import arkhipov.bank.repositories.PersonRepository;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAll();
    }

    @PostMapping("transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction, @RequestParam(value = "debitId") Integer debitId, @RequestParam(value = "refillId") Integer refillId) {
        return transactionRepository.execute(transaction, debitId, refillId);
    }
}