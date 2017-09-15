package arkhipov.bank.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private long amount;
    
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "debitAccount")
    private List<Transaction> debitTransactionList;

    @OneToMany(mappedBy = "refillAccount")
    private List<Transaction> refillTransactionList;
    
    public Account() {
    }

    public Account(Integer id, long amount) {
        super(id);
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public List<Transaction> getDebitTransactionList() {
        return debitTransactionList;
    }

    public List<Transaction> getRefillTransactionList() {
        return refillTransactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + user.getId() +
                ", id=" + getId() +
                ", amount=" + amount +
                '}';
    }
}
