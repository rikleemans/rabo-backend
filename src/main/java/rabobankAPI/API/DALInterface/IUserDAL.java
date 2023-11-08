package rabobankAPI.API.DALInterface;

import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.Charity;

import java.util.List;

public interface IUserDAL {
    AppUser findUserByUsername(String username);

    boolean signup(AppUser appUser);

    boolean updateUser(AppUser appUser);

    boolean highlightcharity(Charity charity);

    AppUser findUserByBankAccountId(Long receiveId);

    AppUser getUserByID(Long id);

    List<AppUser> getUserByPartUsername(String username);
    AppUser saveImage (AppUser appUser);
    AppUser getPictureById(Long id);

}
