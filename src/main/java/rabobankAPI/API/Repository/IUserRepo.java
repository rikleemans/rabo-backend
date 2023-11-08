package rabobankAPI.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.Model.AppUser;

import java.util.List;

@Repository("UserRepo")
public interface IUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
    AppUser findAppUserByBankAccountId(Long receiveId);
    List<AppUser> findAppUserByUsernameContaining(String username);
}
