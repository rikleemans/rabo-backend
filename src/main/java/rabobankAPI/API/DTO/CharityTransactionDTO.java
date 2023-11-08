package rabobankAPI.API.DTO;

        import lombok.Getter;

        import java.util.Date;

@Getter
public class CharityTransactionDTO {

    private double amount;
    private String description;
    private Date date;

    public CharityTransactionDTO(double amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
}
