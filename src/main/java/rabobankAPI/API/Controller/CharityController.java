package rabobankAPI.API.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.ServiceInterface.ICharityService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/charity")
public class CharityController {

    private final ICharityService iCharityService;

public CharityController(@Qualifier("CharityService") ICharityService iCharityService) {
        this.iCharityService = iCharityService;
    }

    @GetMapping("{id}")
    //GET at http://localhost:8082/charity/{id}
    public ResponseEntity<Charity> getCharityById(@PathVariable(value = "id") Long id) {
        Charity result = iCharityService.getCharityById(id);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        String e = "Charity was not found.";
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @GetMapping //GET at http://localhost:8082/charity
    public ResponseEntity<List<Charity>> getAllCharities() {
        List<Charity> charities = null;
        charities = iCharityService.getAllCharities();

        if (charities != null) {
            return ResponseEntity.ok().body(charities);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Charity> createCharity(@RequestBody CharityDTO charityDTO) {
        Charity result = iCharityService.createCharity(charityDTO);
        if (result != null) {
            String url = "charity" + "/" + result.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
        String entity = "Charity was not created. Please try again later.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @DeleteMapping("{id}")
    //DELETE at http://localhost:8082/charity/3
    public ResponseEntity deleteCharity(@PathVariable Long id) {
        iCharityService.deleteCharity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    //PUT at http://localhost:8082/charity/
    public ResponseEntity<Charity> updateCharity(@RequestBody Charity charity) {
        if (iCharityService.updateCharity(charity)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity("Charity was not updated", HttpStatus.NOT_FOUND);
    }
}
