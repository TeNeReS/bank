package arkhipov.bank.web;

import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    private TransactionRepository repository;

    @Autowired
    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @GetMapping("/filter")
    public List<Transaction> getBetween(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, @RequestParam("userId") Integer userId) {
        return repository.getBetweenAndByUserId(startDate, endDate, userId);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction, Integer debitId, Integer refillId) {
        return repository.execute(transaction, debitId, refillId);
    }
}