package arkhipov.bank.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private Integer userId;

    private long amount;

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
}
