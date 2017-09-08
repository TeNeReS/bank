package arkhipov.bank.repositories;

import arkhipov.bank.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
}