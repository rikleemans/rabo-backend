package rabobankAPI.API.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rabobankAPI.API.DTO.UserDTO;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.ServiceInterface.IBankAccountService;
import rabobankAPI.API.ServiceInterface.IUserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final IBankAccountService bankAccountService;

    public UserController(@Qualifier("UserService") IUserService userService, @Qualifier("BankAccountService") IBankAccountService bankAccountService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByToken(Authentication token) {
        UserDTO userDTO = userService.getUserInfo(token);
        if (userDTO.getUsername() != null && !Objects.equals(userDTO.getUsername(), ""))
            return new ResponseEntity(userDTO, HttpStatus.OK);

        String entity = "user could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/part/{username}")
    public ResponseEntity<List<UserDTO>> getUserByPartUsername(@PathVariable("username") String username){
        List<UserDTO> userDTO = userService.getUserByPartUsername(username);
        if (userDTO != null && !userDTO.isEmpty())
            return new ResponseEntity(userDTO, HttpStatus.OK);

        String entity = "user could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/picture/{id}")
    //POST at http://localhost:8082/register/picture
    public ResponseEntity<AppUser> saveProfilePicture(@PathVariable Long id, @RequestParam("image") MultipartFile image){
        AppUser result = userService.saveImage(id, image);
        if (result != null) {
            return new ResponseEntity( HttpStatus.CREATED);
        }
        String entity = "Charity was not created. Please try again later.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/picture/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    //GET at http://localhost:8082/picture/{id}
    public ResponseEntity<byte[]> getPictureById(@PathVariable(value = "id") Long id) {
        AppUser result = userService.getPictureById(id);
        if (result != null) {
            byte[] encode = java.util.Base64.getEncoder().encode(result.getImage());

            return ResponseEntity.ok().body(encode);
        }
        String e = "Image was not found.";
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }
    @GetMapping(value="/bankaccount")
    public ResponseEntity<BankAccount> getBankAccountById(@RequestParam(value = "id") Long id) {
        BankAccount result = bankAccountService.existBankAccountById(id);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        String e = "Bank account was not found.";
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }


}
