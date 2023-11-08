package rabobankAPI.API.DTO;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserTransactionDTO {

    private double amount;
    private String receiverName;
    private String description;
    private Date date;

    public UserTransactionDTO(double amount, String receiverName, String description, Date date) {
        this.amount = amount;
        this.receiverName = receiverName;
        this.description = description;
        this.date = date;
    }
}
