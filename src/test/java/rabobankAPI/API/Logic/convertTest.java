package rabobankAPI.API.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rabobankAPI.API.DTO.*;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Model.Transaction;
import rabobankAPI.API.Service.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class convertTest {

    @Test
    void toAppUser() {
        AppUserDTO appUserDTO = new AppUserDTO("test", "test");
        AppUser userModel = new AppUser("test", "test");

        AppUser result = convert.toAppUser(appUserDTO);

        assertEquals(result.getUsername(), userModel.getUsername());
        assertEquals(result.getPassword(), userModel.getPassword());
    }

    @Test
    void toCharity() {
        CharityDTO charityDTO = new CharityDTO("test", "test","test@test.nl", "google.nl");
        Charity charity = new Charity("test", "test","test@test.nl", "google.nl");

        Charity result = convert.toCharity(charityDTO);

        assertEquals(result.getName(), charity.getName());
        assertEquals(result.getDescription(), charity.getDescription());
        assertEquals(result.getEmail(), charity.getEmail());
        assertEquals(result.getVideoLink(), charity.getVideoLink());
    }

    @Test
    void toProduct() {
        ProductDTO productDTO = new ProductDTO("google.nl");
        Product product = new Product("google.nl");

        Product result = convert.toProduct(productDTO);

        assertEquals(result.getLink(), product.getLink());
    }

    @Test
    void toTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO(5, "test", 1L, 2l);
        Transaction transaction = new Transaction(5, "test", 1L, 2l, new Date());

        Transaction result = convert.toTransaction(transactionDTO);

        assertEquals(result.getAmount(), transaction.getAmount());
        assertEquals(result.getDescription(), transaction.getDescription());
        assertEquals(result.getSendingBankAccountId(), transaction.getSendingBankAccountId());
        assertEquals(result.getReceivingBankAccountId(), transaction.getReceivingBankAccountId());
        assertEquals(result.getDate(), transaction.getDate());
    }
}