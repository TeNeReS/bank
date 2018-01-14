package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private CrudTransactionRepository crudTransactionRepository;

    @Autowired
    public TransactionRepositoryImpl(CrudTransactionRepository crudTransactionRepository) {
        this.crudTransactionRepository = crudTransactionRepository;
    }

    public Page<Transaction> getAll(Pageable pageable) {
        return crudTransactionRepository.findAll(pageable);
    }

    public Page<Transaction> getBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        if (startDate == null) startDate = LocalDateTime.of(1000, 1, 1, 0, 0, 0);
        if (endDate == null) endDate = LocalDateTime.now();
        return crudTransactionRepository.findByDateBetween(startDate, endDate, pageable);
    }

    public List<Transaction> getByPersonId(int personId) {
        return crudTransactionRepository.findByPersonId(personId);
    }

    @Transactional
    public Transaction execute(Transaction transaction){
        Account debitAccount = transaction.getDebitAccount();
        Account refillAccount = transaction.getRefillAccount();
        if (refillAccount != null && debitAccount != null) {
            crudTransactionRepository.transfer(debitAccount.getId(), -transaction.getAmount());
            crudTransactionRepository.transfer(refillAccount.getId(), transaction.getAmount());
        }
        else if (refillAccount == null && debitAccount != null) {
            crudTransactionRepository.transfer(debitAccount.getId(), -transaction.getAmount());
        }
        else if (refillAccount != null) {
            crudTransactionRepository.transfer(refillAccount.getId(), transaction.getAmount());
        }
        return crudTransactionRepository.save(transaction);
    }
}