package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private CrudAccountRepository crudAccountRepository;

    private UserRepository userRepository;

    @Autowired
    public AccountRepositoryImpl(CrudAccountRepository crudAccountRepository, UserRepository userRepository) {
        this.crudAccountRepository = crudAccountRepository;
        this.userRepository = userRepository;
    }

    public List<Account> getAll(int userId) {
        return crudAccountRepository.getAll(userId);
    }

    @Transactional
    public Account create(Account account, int userId) {
        account.setUser(userRepository.findOne(userId));
        return crudAccountRepository.save(account);
    }
}
