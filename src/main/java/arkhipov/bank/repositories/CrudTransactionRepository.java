package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudTransactionRepository extends CrudRepository<Transaction, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.amount=a.amount+:amount WHERE a.id=:id")
    void transfer(@Param("id") int id, @Param("amount") long amount);

    List<Transaction> findByDateBetween(Date startDate, Date endDate);

    @Query("SELECT t FROM Transaction t WHERE t.debitAccount.user.id=:userId OR t.refillAccount.user.id=:userId")
    List<Transaction> findByUserId(@Param("userId") int userId);

    @Query("SELECT t FROM Transaction t WHERE (t.debitAccount.user.id=:userId OR t.refillAccount.user.id=:userId) AND t.date between :startDate and :endDate")
    List<Transaction> findByDateBetweenAndUserId(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId")int userId);
}