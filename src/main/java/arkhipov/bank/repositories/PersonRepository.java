package arkhipov.bank.repositories;

import arkhipov.bank.models.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @EntityGraph(attributePaths = "accountList")
    @Query("SELECT u FROM Person u WHERE u.id=:id")
    Person getWithAccounts (@Param("id") int id);
}