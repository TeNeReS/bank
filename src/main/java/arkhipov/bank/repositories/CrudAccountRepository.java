package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudAccountRepository extends CrudRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.person.id=:personId")
    List<Account> getAll(@Param("personId") int personId);
}
