package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
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