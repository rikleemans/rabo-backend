package rabobankAPI.API.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Logic.Validation;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.MockDAL.MockBankAccountDAL;
import rabobankAPI.API.MockDAL.MockRegisterDal;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Service.BankAccountService;
import rabobankAPI.API.Service.UserService;
import rabobankAPI.API.ServiceInterface.IBankAccountService;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    private RegisterController registerController;
    private UserController userController;

    @BeforeEach
    void setUp() {
        IUserDAL userDAL = new MockRegisterDal();
        convert convert = new convert();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Validation validation = new Validation();
        IBankAccountDAL bankAccountDal= new MockBankAccountDAL();
        BankAccountService bankAccountService = new BankAccountService(bankAccountDal);
        UserService userService = new UserService(userDAL, bCryptPasswordEncoder, bankAccountService, convert, validation);

        this.registerController = new RegisterController(userService);
        this.userController = new UserController(userService, bankAccountService);
    }

    @Test
    void registerUserHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CREATED;
        String expectedBody = ("user created with username and password: Rabobank+Rabobank1!");
        AppUserDTO appUserDTO = new AppUserDTO("Rabobank", "Rabobank1!");

        //act
        ResponseEntity<?> responseEntity = registerController.registerUser(appUserDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        String resultBody = (String) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void createUserNullLink() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CONFLICT;
        String expectedBody = ("User with username rabo already exists.");
        AppUserDTO appUserDTO = new AppUserDTO("rabo", "rabo!");

        //act
        ResponseEntity<?> responseEntity = registerController.registerUser(appUserDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        String resultBody = (String) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }
}