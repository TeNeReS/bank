package arkhipov.bank.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Transaction extends BaseEntity {
    private long amount;

    private String description;

    private Date date = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account refillAccount;

    public Transaction(long amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}