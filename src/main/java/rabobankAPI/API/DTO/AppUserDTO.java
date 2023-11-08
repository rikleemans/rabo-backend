package rabobankAPI.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
public class AppUserDTO {
    private String username;
    private String password;
    private String email;
    private Long bankAccountId;

    public AppUserDTO() {
    }
}
