package arkhipov.bank.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    private Integer userId;

    private Integer otpravId;

    private Integer poluchId;

    private String description;

    private LocalDateTime dateTime;

    public Transaction(Integer userId, Integer otpravId, Integer poluchId, String description) {
        this.userId = userId;
        this.otpravId = otpravId;
        this.poluchId = poluchId;
        this.description = description;
    }


    public Integer getOtpravId() {
        return otpravId;
    }

    public void setOtpravId(Integer otpravId) {
        this.otpravId = otpravId;
    }

    public Integer getPoluchId() {
        return poluchId;
    }

    public void setPoluchId(Integer poluchId) {
        this.poluchId = poluchId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
