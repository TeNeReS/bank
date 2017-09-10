package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    private Integer debitId;

    private Integer refillId;

    private long amount;

    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date date = new Date();

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Transaction() {
    }

    public Transaction(Integer otpravId, Integer poluchId, long amount, String description) {
        this.debitId = otpravId;
        this.refillId = poluchId;
        this.amount = amount;
        this.description = description;
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

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + getId() +
                ", debitId=" + debitId +
                ", refillId=" + refillId +
                ", amount=" + amount +
                ", description=" + description +
                ", date=" + date +
                '}';
    }
}