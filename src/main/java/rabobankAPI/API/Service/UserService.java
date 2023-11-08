package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.DTO.UserDTO;
import rabobankAPI.API.Logic.Validation;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.ServiceInterface.IBankAccountService;
import rabobankAPI.API.ServiceInterface.IUserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserService implements IUserService {
    private final IUserDAL iUserDAL;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IBankAccountService iBankAccountService;
    private final convert convert;
    private final Validation validation;

    public UserService(@Qualifier("UserDAL") IUserDAL iUserDAL, @Qualifier("BCryptPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, @Qualifier("BankAccountService") IBankAccountService iBankAccountService, @Qualifier("convert") convert convert, @Qualifier("Validation") Validation validation) {
        this.iUserDAL = iUserDAL;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.iBankAccountService = iBankAccountService;
        this.convert = convert;
        this.validation = validation;
    }

    public boolean signUp(AppUserDTO appUserDTO) {
        //check user input not null
        if (appUserDTO.getUsername() == null || appUserDTO.getPassword() == null)
            return false;

        //convert dto to model
        AppUser appUser = convert.toAppUser(appUserDTO);

        //add bankaccount to appuser
        BankAccount bankAccount = iBankAccountService.createBankAccount();
        appUser.setBankAccountId(bankAccount.getId());

        //check on valid fields
        if (!validation.appUser(appUser))
            return false;

        //change password with BCrypt
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        //save user info
        return iUserDAL.signup(appUser);
    }

    public AppUser getUserByBankAccountId(Long receiveId) {
        return iUserDAL.findUserByBankAccountId(receiveId);
    }

    public UserDTO getUserInfo(Authentication token) {
        AppUser appUser = iUserDAL.findUserByUsername(token.getName());
        return convert.toUserDTO(appUser);
    }

    public UserDTO getUserByUsername(String username) {
        AppUser appUser = iUserDAL.findUserByUsername(username);
        return convert.toUserDTO(appUser);
    }

    public List<UserDTO> getUserByPartUsername(String username) {
        List<AppUser> appUsers = iUserDAL.getUserByPartUsername(username);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            userDTOS.add(convert.toUserDTO(appUser));
        }
        return userDTOS;
    }

    public AppUser saveImage (Long id, MultipartFile image){
        try{
            AppUser result = iUserDAL.getUserByID(id);
            AppUser FileDB = new AppUser (id, result.getUsername(), result.getPassword(), result.getEmail(),result.getBankAccountId(), image.getContentType(), image.getBytes());
            return iUserDAL.saveImage(FileDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppUser getPictureById(Long id) {
        try {
            return iUserDAL.getPictureById(id);
        } catch (Exception e) {
            AppUser result = iUserDAL.getPictureById(id);
            return result;
        }
    }


    public boolean updateUser(AppUserDTO appUserDTO) {
        return false;
    }

    public boolean highlightcharity(CharityDTO charityDTO) {
        return false;
    }

    public boolean checkUser(String username) {
        return true;
    }

}
