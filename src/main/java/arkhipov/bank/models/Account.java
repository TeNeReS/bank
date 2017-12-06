package arkhipov.bank.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {
    private long amount;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    @OneToMany(mappedBy = "debitAccount")
    private List<Transaction> debitTransactionList;

    @OneToMany(mappedBy = "refillAccount")
    private List<Transaction> refillTransactionList;

    public Account(long amount) {
        this.amount = amount;
    }
}
