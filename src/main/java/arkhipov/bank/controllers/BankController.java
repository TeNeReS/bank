package arkhipov.bank.controllers;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Person;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.AccountRepository;
import arkhipov.bank.repositories.PersonRepository;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("persons")
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @RequestMapping("persons/{id}/accounts")
    public List<Account> getAccountsByPersonId(@PathVariable(value = "id") int personId) {
        return accountRepository.getAll(personId);
    }

    @RequestMapping("transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAll();
    }
}