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

    private CrudAccountRepository accountRepository;

    @Autowired
    public TransactionRepositoryImpl(CrudTransactionRepository crudTransactionRepository, CrudAccountRepository accountRepository) {
        this.crudTransactionRepository = crudTransactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getAll() {
        return (List<Transaction>) crudTransactionRepository.findAll();
    }

    public List<Transaction> getBetween(Date startDate, Date endDate) {
        if (startDate == null) startDate = new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 365);
        if (endDate == null) endDate = new Date(new Date().getTime() + 1000 * 60 * 60 * 365);
        return crudTransactionRepository.findByDateBetween(startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)));
    }

    public List<Transaction> getByUserId(int userId) {
        return crudTransactionRepository.findByUserId(userId);
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