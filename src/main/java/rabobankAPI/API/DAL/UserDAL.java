package rabobankAPI.API.DAL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Repository.IUserRepo;

import java.util.List;

@Repository("UserDAL")
public class UserDAL implements IUserDAL {
    private final IUserRepo iUserRepo;

    public UserDAL(@Qualifier("UserRepo") IUserRepo iUserRepo) {
        this.iUserRepo = iUserRepo;
    }

    public AppUser findUserByUsername(String username) {
        return iUserRepo.findUserByUsername(username);
    }

    public boolean signup(AppUser appUser) {
        iUserRepo.save(appUser);
        return true;
    }

    public boolean updateUser(AppUser appUser) {
        iUserRepo.save(appUser);
        return true;
    }

    public AppUser saveImage(AppUser appUser) {
        return iUserRepo.save(appUser);
    }

    public AppUser getPictureById(Long id) {
        return iUserRepo.getById(id);
    }


    public boolean highlightcharity(Charity charity) {
        return false;
    }

    public AppUser findUserByBankAccountId(Long receiveId) {
        return iUserRepo.findAppUserByBankAccountId(receiveId);
    }

    public AppUser getUserByID(Long id) {
        return iUserRepo.getById(id);
    }

    public List<AppUser> getUserByPartUsername(String username) {
        return iUserRepo.findAppUserByUsernameContaining(username);
    }
}