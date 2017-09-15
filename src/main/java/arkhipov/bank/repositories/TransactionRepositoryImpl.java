package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private CrudTransactionRepository crudTransactionRepository;

    private AccountRepository accountRepository;

    @Autowired
    public TransactionRepositoryImpl(CrudTransactionRepository crudTransactionRepository, AccountRepository accountRepository) {
        this.crudTransactionRepository = crudTransactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> findAll() {
        return (List<Transaction>) crudTransactionRepository.findAll();
    }

    public List<Transaction> getBetweenAndByUserId(Date startDate, Date endDate, Integer userId) {
        if (userId == null) return crudTransactionRepository.findByDateBetween(startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)));
        else if (startDate == null && endDate == null) return crudTransactionRepository.findByUserId(userId);
        return crudTransactionRepository.findByDateBetweenAndUserId(startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)), userId);
    }

    @Transactional
    public Transaction execute(Transaction transaction, Integer debitId, Integer refillId){
        if (refillId != null && debitId != null) {
            crudTransactionRepository.transfer(debitId, -transaction.getAmount());
            crudTransactionRepository.transfer(refillId, transaction.getAmount());
        }
        else if (refillId == null && debitId != null) {
            crudTransactionRepository.transfer(debitId, -transaction.getAmount());
        }
        else if (refillId != null) {
            crudTransactionRepository.transfer(refillId, transaction.getAmount());
        }
        if (debitId != null) transaction.setDebitAccount(accountRepository.findOne(debitId));
        if (refillId != null) transaction.setRefillAccount(accountRepository.findOne(refillId));
        return crudTransactionRepository.save(transaction);
    }
}