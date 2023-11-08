package rabobankAPI.API.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String description;
    private String email;
    private String videoLink;
    @NotNull
    private Long bankAccountId;
    
    public Charity(String name, String description, String email, String videoLink) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.videoLink = videoLink;
    }

    public Charity(String name, String description, String email, Long bankAccountId) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.bankAccountId = bankAccountId;
    }
}
