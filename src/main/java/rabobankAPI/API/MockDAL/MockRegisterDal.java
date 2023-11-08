package rabobankAPI.API.MockDAL;

import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MockRegisterDal implements IUserDAL {
    private List<AppUser> users;

    public MockRegisterDal() {
        this.users = new ArrayList<>();
        users.add(new AppUser(1L, "test", "test", "email@email.com",1L, "image/jpeg", new byte[1]));
        users.add(new AppUser(2L, "test2", "test2","email2@email.com", 2L, "image/jpeg", new byte[1]));
        users.add(new AppUser(3L, "test3", "test3", "email3@email.com",3L, "image/jpeg", new byte[1]));
        users.add(new AppUser(4L, "test4", "test4","email4@email.com", 4L,  "image/jpeg", new byte[1]));
    }

    public AppUser findUserByUsername(String username) {
        return null;
    }

    public boolean signup(AppUser appUser) {
        Long id = users.get(users.size()-1).getId()+1;
        appUser.setId(id);
        users.add(appUser);
        return true;
    }

    public boolean updateUser(AppUser appUser) {
        return false;
    }

    public boolean highlightcharity(Charity charity) {
        return false;
    }

    public AppUser findUserByBankAccountId(Long receiveId) {
        for(AppUser appUser: users){
            if(appUser.getBankAccountId().equals(receiveId)){
                return appUser;
            }
        }
        return null;
    }

    public AppUser getUserByID(Long id) {
        for(AppUser appUser: users){
            if(appUser.getBankAccountId().equals(id)){
                return appUser;
            }
        }
        return null;
    }

    public List<AppUser> getUserByPartUsername(String username) {
        //todo: make this method
        return null;
    }

    public AppUser saveImage(AppUser appUser) {
        return null;
    }

    public AppUser getPictureById(Long id) {
        return null;
    }
}
