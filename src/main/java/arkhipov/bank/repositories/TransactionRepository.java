package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();

    List<Transaction> getBetween(Date startDate, Date endDate);

    Transaction execute(Transaction transaction);
}