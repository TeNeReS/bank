package arkhipov.bank.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
