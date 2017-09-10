package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CrudTransactionRepository extends CrudRepository<Transaction, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.amount=a.amount+:amount WHERE a.id=:id")
    void transfer(@Param("id") int id, @Param("amount") long amount);

    List<Transaction> findByDateBetween(Date startDate, Date endDate);
}