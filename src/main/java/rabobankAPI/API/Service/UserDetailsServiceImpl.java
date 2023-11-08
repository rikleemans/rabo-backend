package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DALInterface.IUserDAL;
import rabobankAPI.API.Model.AppUser;

import static java.util.Collections.emptyList;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserDAL iUserDAL;

    public UserDetailsServiceImpl(@Qualifier("UserDAL") IUserDAL iUserDAL) {
        this.iUserDAL = iUserDAL;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iUserDAL.findUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
    }
}
