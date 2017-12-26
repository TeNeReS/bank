package arkhipov.bank.models;

import arkhipov.bank.util.LocalDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Transaction extends BaseEntity {
    private long amount;

    private String description;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account refillAccount;

    public Transaction(long amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}