package arkhipov.bank.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    private long amount;

    private String description;

    private Date date = new Date();

    @JoinColumn(name = "debit_account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account debitAccount;

    @JoinColumn(name = "refill_account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account refillAccount;

    public Transaction() {
    }

    public Transaction(long amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccountId) {
        this.debitAccount = debitAccountId;
    }

    public Account getRefillAccount() {
        return refillAccount;
    }

    public void setRefillAccount(Account refillAccountId) {
        this.refillAccount = refillAccountId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        String debitAccountId =  getDebitAccount() != null ? debitAccount.getId().toString() : "Empty";
        String refillAccountId =  getRefillAccount() != null ? refillAccount.getId().toString() : "Empty";
        String desc = description != null ? description : "Empty";
        return "Transaction{" +
                "Id=" + getId() +
                ", debitAccountId=" + debitAccountId +
                ", refillAccountId=" + refillAccountId +
                ", amount=" + amount +
                ", description=" + desc +
                ", date=" + date +
                '}';
    }
}