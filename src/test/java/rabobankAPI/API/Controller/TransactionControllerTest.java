package rabobankAPI.API.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.DALInterface.ICharityDAL;
import rabobankAPI.API.DALInterface.ITransactionDAL;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.DTO.TransactionDTO;
import rabobankAPI.API.Logic.Validation;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.MockDAL.MockBankAccountDAL;
import rabobankAPI.API.MockDAL.MockCharityDAL;
import rabobankAPI.API.MockDAL.MockRegisterDal;
import rabobankAPI.API.MockDAL.MockTransactionDAL;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Model.Transaction;
import rabobankAPI.API.Service.BankAccountService;
import rabobankAPI.API.Service.CharityService;
import rabobankAPI.API.Service.TransactionService;
import rabobankAPI.API.Service.UserService;

import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {

    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        ITransactionDAL transactionDAL = new MockTransactionDAL();
        ICharityDAL charityDAL = new MockCharityDAL();
        IUserDAL userDAL = new MockRegisterDal();
        convert convert = new convert();
        Validation validation = new Validation();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        IBankAccountDAL bankAccountDal= new MockBankAccountDAL();
        BankAccountService bankAccountService = new BankAccountService(bankAccountDal);
        CharityService charityService = new CharityService(charityDAL, bankAccountService, validation, convert);
        UserService userService = new UserService(userDAL, bCryptPasswordEncoder, bankAccountService, convert, validation);
        TransactionService transactionService = new TransactionService(transactionDAL, bankAccountService, convert, charityService, userService);
        this.transactionController = new TransactionController(transactionService);
    }

    @Test
    void createTransactionCharityHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CREATED;
        URI expectedBody = URI.create("transaction/5");
        TransactionDTO transactionDTO = new TransactionDTO(25, "transaction", 1L, 2L);
        int expectedSize = 5;

        //act
        ResponseEntity<?> responseEntity = transactionController.createTransactionCharity(transactionDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        URI resultBody = (URI) responseEntity.getBody();

        //Second act
        ResponseEntity<?> responseEntityList = transactionController.getAlltransactions();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createTransactionCharityFail() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CONFLICT;
        String expectedBody = ("Transaction was not created. Please try again later.");
        TransactionDTO transactionDTO = new TransactionDTO();

        //act
        ResponseEntity<?> responseEntity = transactionController.createTransactionCharity(transactionDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        String resultBody = (String) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void createTransactionUserHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CREATED;
        URI expectedBody = URI.create("transaction/5");
        TransactionDTO transactionDTO = new TransactionDTO(25, "transaction", 1L, 2L);
        int expectedSize = 5;

        //act
        ResponseEntity<?> responseEntity = transactionController.createTransactionUser(transactionDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        URI resultBody = (URI) responseEntity.getBody();

        //Second act
        ResponseEntity<?> responseEntityList = transactionController.getAlltransactions();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createTransactionUserFail() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CONFLICT;
        String expectedBody = ("Transaction was not created. Please try again later.");
        TransactionDTO transactionDTO = new TransactionDTO();

        //act
        ResponseEntity<?> responseEntity = transactionController.createTransactionCharity(transactionDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        String resultBody = (String) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void getTransactionById() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Transaction expectedBody = new Transaction(1L, 25, "test", 1L, 2L,new Date(10-03-2015));

        //act
        ResponseEntity<?> responseEntity = transactionController.getTransactionById(1L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        Transaction resultBody = (Transaction) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void getTransactionByNegativeId() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = transactionController.getTransactionById(-5L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
    }

    @Test
    void getTransactionByZeroId() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = transactionController.getTransactionById(0L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
    }

    @Test
    void getAllTransactionsHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = transactionController.getAlltransactions();
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        int resultSize = ((List<Product>) responseEntity.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
    }
}