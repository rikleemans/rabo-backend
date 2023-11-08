package rabobankAPI.API.Logic;

import org.springframework.stereotype.Service;
import rabobankAPI.API.DTO.*;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("convert")
public class convert {
    public static AppUser toAppUser(AppUserDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String email = dto.getEmail();
        Long bankAccountId = dto.getBankAccountId();
        return new AppUser(username, password, email, bankAccountId);
    }

    public static UserDTO toUserDTO(AppUser appUser) {
        Long id = appUser.getId();
        String name = appUser.getUsername();
        String email = appUser.getEmail();
        Long bankAccountId = appUser.getBankAccountId();
        return new UserDTO(id, name, email, bankAccountId);
    }

    public static Charity toCharity(CharityDTO dto) {
        String email = dto.getEmail();
        String name = dto.getName();
        String description = dto.getDescription();
        String videoLink = dto.getVideoLink() == null ? "" : dto.getVideoLink();
        return new Charity(name, description, email, videoLink);
    }

    public static Product toProduct(ProductDTO dto) {
        String link = dto.getLink();
        String imageLink = dto.getImageLink();
        String name= dto.getName();
        return new Product(link, imageLink, name);
    }

    public static Transaction toTransaction(TransactionDTO dto) {
        double amount = dto.getAmount();
        String description = dto.getDescription();
        Long sendingBankAccountId = dto.getSendingBackAccountId();
        Long receivingBankAccountId = dto.getReceivingBankAccountId();
        Date date = new Date();

        return new Transaction(amount, description, sendingBankAccountId, receivingBankAccountId, date);
    }

    public static List<CharityTransactionDTO> toCharityTransactionDTOList(List<Transaction> transactions) {
        List<CharityTransactionDTO> returnResult = new ArrayList<>();
        for (Transaction transaction : transactions) {
            returnResult.add(toCharityTransactionDTO(transaction));
        }
        return returnResult;
    }

    private static CharityTransactionDTO toCharityTransactionDTO(Transaction transaction) {
        double amount = transaction.getAmount();
        String description = transaction.getDescription();
        Date date = transaction.getDate();

        return new CharityTransactionDTO(amount, description, date);
    }
}
