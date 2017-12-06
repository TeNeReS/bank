package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private CrudAccountRepository crudAccountRepository;

    private PersonRepository personRepository;

    @Autowired
    public AccountRepositoryImpl(CrudAccountRepository crudAccountRepository, PersonRepository personRepository) {
        this.crudAccountRepository = crudAccountRepository;
        this.personRepository = personRepository;
    }

    public List<Account> getAll(int personId) {
        return crudAccountRepository.getAll(personId);
    }

    @Transactional
    public Account create(Account account, int personId) {
        account.setPerson(personRepository.findOne(personId));
        return crudAccountRepository.save(account);
    }
}
