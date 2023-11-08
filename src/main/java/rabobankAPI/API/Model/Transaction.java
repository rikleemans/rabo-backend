package rabobankAPI.API.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private String description;
    private Long sendingBankAccountId;
    private Long receivingBankAccountId;
    private Date date;

public Transaction(double amount, String description, Long sendingBankAccountId, Long receivingBankAccountId, Date date) {
        this.amount = amount;
        this.description = description;
        this.sendingBankAccountId = sendingBankAccountId;
        this.receivingBankAccountId = receivingBankAccountId;
        this.date = date;
    }
}
