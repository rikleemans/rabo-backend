package rabobankAPI.API.ServiceInterface;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.DTO.UserDTO;
import rabobankAPI.API.Model.AppUser;

import java.util.List;

public interface IUserService {
    boolean signUp(AppUserDTO appUserDTO);

    UserDTO getUserInfo(Authentication token);

    boolean updateUser(AppUserDTO appUserDTO);

    boolean highlightcharity(CharityDTO charityDTO);

    boolean checkUser(String username);

    AppUser getUserByBankAccountId(Long receiveId);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getUserByPartUsername(String username);
    AppUser saveImage(Long id, MultipartFile image);
    AppUser getPictureById(Long id);

}
