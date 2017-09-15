package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();

    List<Transaction> getBetweenAndByUserId(Date startDate, Date endDate, Integer userId);

    Transaction execute(Transaction transaction, Integer debitId, Integer refillId);
}