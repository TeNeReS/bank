package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    Page<Transaction> getAll(Pageable pageable);

    List<Transaction> getBetween(Date startDate, Date endDate);

    List<Transaction> getByPersonId(int personId);

    Transaction execute(Transaction transaction);
}