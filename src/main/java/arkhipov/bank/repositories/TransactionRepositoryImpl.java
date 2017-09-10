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

    @Autowired
    public TransactionRepositoryImpl(CrudTransactionRepository crudTransactionRepository) {
        this.crudTransactionRepository = crudTransactionRepository;
    }

    public List<Transaction> findAll() {
        return (List<Transaction>) crudTransactionRepository.findAll();
    }

    public List<Transaction> getBetween(Date startDate, Date endDate) {
        return crudTransactionRepository.findByDateBetween(startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)));
    }

    @Transactional
    public Transaction execute(Transaction transaction){
        if (transaction.getRefillId() != null && transaction.getDebitId() != null) {
            crudTransactionRepository.transfer(transaction.getDebitId(), -transaction.getAmount());
            crudTransactionRepository.transfer(transaction.getRefillId(), transaction.getAmount());
        }
        else if (transaction.getRefillId() == null && transaction.getDebitId() != null) {
            crudTransactionRepository.transfer(transaction.getDebitId(), -transaction.getAmount());
        }
        else if (transaction.getRefillId() != null && transaction.getDebitId() == null) {
            crudTransactionRepository.transfer(transaction.getRefillId(), transaction.getAmount());
        }
        return crudTransactionRepository.save(transaction);
    }
}