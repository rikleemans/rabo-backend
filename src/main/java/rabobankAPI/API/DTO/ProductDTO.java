package rabobankAPI.API.DTO;

import lombok.Getter;

@Getter
public class ProductDTO {
    private String link;
    private String imageLink;
    private String name;

    public ProductDTO() {
    }

    public ProductDTO(String link, String imageLink, String name) {
        this.link = link;
        this.imageLink = imageLink;
        this.name = name;
    }


}
