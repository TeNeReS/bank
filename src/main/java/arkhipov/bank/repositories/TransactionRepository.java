package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAll();

    List<Transaction> getBetween(Date startDate, Date endDate);

    List<Transaction> getByUserId(int userId);

    Transaction execute(Transaction transaction, Integer debitId, Integer refillId);
}