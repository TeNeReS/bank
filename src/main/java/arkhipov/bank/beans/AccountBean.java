package arkhipov.bank.beans;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.AccountRepository;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class AccountBean implements Serializable{
    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    private int userId;

    private long amount;

    private Integer debitId;

    private Integer refillId;

    private String transactionDescription;

    private List<Integer> accountIdList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Integer getDebitId() {
        return debitId;
    }

    public void setDebitId(Integer debitId) {
        this.debitId = debitId;
    }

    public Integer getRefillId() {
        return refillId;
    }

    public void setRefillId(Integer refillId) {
        this.refillId = refillId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public List<Integer> getAccountIdList() {
        return accountIdList;
    }

    public void setAccountIdList(List<Integer> accountIdList) {
        this.accountIdList = accountIdList;
    }

    @Autowired
    public AccountBean(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Account> getAll() {
        List<Account> accountList = accountRepository.getAll(userId);
        List<Integer> accountIdList = new ArrayList<Integer>();
        for (Account account : accountList) {
            accountIdList.add(account.getId());
        }
        setAccountIdList(accountIdList);
        return accountList;
    }

    public void create(){
        Account created = new Account(getAmount());
        accountRepository.create(created, getUserId());
        clearForm();
    }

    public void transfer() {
        transactionRepository.execute(new Transaction(getAmount(), getTransactionDescription()), getDebitId(), getRefillId());
        clearForm();
    }

    private void clearForm(){
        setAmount(0);
        setDebitId(null);
        setRefillId(null);
        setTransactionDescription(null);
    }
}
