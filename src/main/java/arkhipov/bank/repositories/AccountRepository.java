package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.userId=:userId")
    List<Account> getAll(@Param("userId") int userId);
}
