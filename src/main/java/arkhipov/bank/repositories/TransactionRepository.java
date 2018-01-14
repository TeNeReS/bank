package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {
    Page<Transaction> getAll(Pageable pageable);

    Page<Transaction> getBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Transaction> getByPersonId(int personId);

    Transaction execute(Transaction transaction);
}