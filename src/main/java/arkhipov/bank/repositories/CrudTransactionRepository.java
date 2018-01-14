package arkhipov.bank.repositories;

import arkhipov.bank.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudTransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.amount=a.amount+:amount WHERE a.id=:id")
    void transfer(@Param("id") int id, @Param("amount") long amount);

    Page<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.debitAccount.person.id=:personId OR t.refillAccount.person.id=:personId")
    List<Transaction> findByPersonId(@Param("personId") int personId);

//    @Query("SELECT t FROM Transaction t WHERE (t.debitAccount.person.id=:personId OR t.refillAccount.person.id=:personId) AND t.date between :startDate and :endDate")
//    List<Transaction> findByDateBetweenAndPersonId(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("personId")int personId);
}