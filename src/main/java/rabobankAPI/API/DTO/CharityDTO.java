package rabobankAPI.API.DTO;

public class CharityDTO {

    private String name;
    private String description;
    private String email;
    private String videoLink;

    public CharityDTO() {
    }

    public CharityDTO(String name, String description, String email) {
        this.name = name;
        this.description = description;
        this.email = email;
    }

    public CharityDTO(String name, String description, String email, String videoLink) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.videoLink = videoLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getVideoLink() {
        return videoLink;
    }
}
