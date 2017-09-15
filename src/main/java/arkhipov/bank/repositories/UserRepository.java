package arkhipov.bank.repositories;

import arkhipov.bank.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer> {
    @EntityGraph(attributePaths = "accountList")
    @Query("SELECT u FROM User u WHERE u.id=:id")
    User getWithAccounts (@Param("id") int id);
}