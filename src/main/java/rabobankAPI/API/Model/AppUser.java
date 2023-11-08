package rabobankAPI.API.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Long bankAccountId;
    private String type;
    private byte[] image;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public AppUser(String username, String password, String email, Long bankAccountId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bankAccountId = bankAccountId;
    }
    public AppUser(Long id,String username, String password,String email, Long bankAccountId, String type, byte[] image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bankAccountId = bankAccountId;
        this.type = type;
        this.image = image;
    }
}
