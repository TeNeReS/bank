package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private Integer userId;

    private long amount;

    @Transient
    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Account() {
    }

    public Account(Integer id, Integer userId, long amount) {
        super(id);
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", id=" + getId() +
                ", amount=" + amount +
                '}';
    }
}
