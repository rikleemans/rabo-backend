package rabobankAPI.API.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.DTO.UserDTO;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public OAuth2LoginSuccessHandler(UserService userService) {
        super();
        setUseReferer(true);
        this.userService = userService;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
            String email = user.getName();
            try {
                userService.getUserByUsername(email);
                System.out.println("User was found");
            } catch(Exception ex) {
                AppUserDTO userToAdd = new AppUserDTO();
                userToAdd.setUsername(user.getFullName().replace(" ", ""));
                userToAdd.setPassword("Pw" + user.getName() + "!");
                userToAdd.setEmail(user.getEmail());
                userService.signUp(userToAdd);
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }


//        super.onAuthenticationSuccess(request, response, authentication);
    }
}
