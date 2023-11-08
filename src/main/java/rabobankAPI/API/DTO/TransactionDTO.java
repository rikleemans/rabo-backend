package rabobankAPI.API.DTO;

public class TransactionDTO {
    private double amount;
    private String description;
    private Long sendingBackAccountId;
    private Long receivingBankAccountId;

    public TransactionDTO(){}

    public TransactionDTO(double amount, String description, Long sendingBackAccountId, Long receivingBankAccountId){
        this.amount = amount;
        this.description = description;
        this.sendingBackAccountId = sendingBackAccountId;
        this.receivingBankAccountId = receivingBankAccountId;
    }

    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public Long getSendingBackAccountId() {
        return sendingBackAccountId;
    }
    public Long getReceivingBankAccountId() {
        return receivingBankAccountId;
    }


}
