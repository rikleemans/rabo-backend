package rabobankAPI.API.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(@Qualifier("UserService") UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    //POST at http://localhost:8082/register/user
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUserDTO appUser) {
        if (!userService.signUp(appUser)) {
            String entity = "User with username " + appUser.getUsername() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        //request registerservice for the just created user for the userID
        String entity = "user created with username and password" + ": " + appUser.getUsername() + "+" + appUser.getPassword(); // url of the created student
        return new ResponseEntity(entity, HttpStatus.CREATED);
    }
}
