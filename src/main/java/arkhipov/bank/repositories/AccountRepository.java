package arkhipov.bank.repositories;

import arkhipov.bank.models.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> getAll(int id);

    Account create (Account account, int personId);
}
