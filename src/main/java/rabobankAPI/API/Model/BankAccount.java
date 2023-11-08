package rabobankAPI.API.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    @NotNull
    private String iban;
    @NotNull
    private double amount;

    public BankAccount(String iban, double amount) {
        this.iban = iban;
        this.amount = amount;
    }
}
