package rabobankAPI.API.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rabobankAPI.API.DAL.BankAccountDAL;
import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.DALInterface.ICharityDAL;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Logic.Validation;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.MockDAL.MockBankAccountDAL;
import rabobankAPI.API.MockDAL.MockCharityDAL;
import rabobankAPI.API.MockDAL.MockRegisterDal;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Service.BankAccountService;
import rabobankAPI.API.Service.CharityService;
import rabobankAPI.API.ServiceInterface.IBankAccountService;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharityControllerTest {

    private CharityController charityController;

    @BeforeEach
    void setUp() {
        ICharityDAL charityDAL = new MockCharityDAL();
        convert convert = new convert();
        Validation validation = new Validation();
        IBankAccountDAL bankAccountDal= new MockBankAccountDAL();
        BankAccountService bankAccountService = new BankAccountService(bankAccountDal);
        CharityService charityService = new CharityService(charityDAL, bankAccountService, validation, convert);
        this.charityController = new CharityController(charityService);
    }

    @Test
    void getCharityById() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Charity expectedBody = new Charity(1L, "www", "desc", "www@www.nl", "link", 1L);

        //act
        ResponseEntity<?> responseEntity = charityController.getCharityById(1L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        Charity resultBody = (Charity) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void getCharityByNullId() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = charityController.getCharityById(0L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
    }

    @Test
    void getCharityByZeroId() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = charityController.getCharityById(-5L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
    }

    @Test
    void getAllCharities() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = charityController.getAllCharities();
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        int resultSize = ((List<Charity>) responseEntity.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createCharityHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CREATED;
        URI expectedBody = URI.create ("charity/5");
        CharityDTO charityDTO = new CharityDTO("Kwf", "desc", "www@www.nl", "link");
        int expectedSize = 5;

        //act
        ResponseEntity<?> responseEntity = charityController.createCharity(charityDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        URI resultBody = (URI) responseEntity.getBody();

        //Second act
        ResponseEntity<?> responseEntityList = charityController.getAllCharities();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createCharityFail() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CONFLICT;
        String expectedBody = ("Charity was not created. Please try again later.");
        CharityDTO charityDTO = new CharityDTO("kwf", "desc", "www@www.nl", "link");
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = charityController.createCharity(charityDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        String resultBody = (String) responseEntity.getBody();

        //Second act
        ResponseEntity<?> responseEntityList = charityController.getAllCharities();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
        assertEquals(expectedSize, resultSize);
    }
}