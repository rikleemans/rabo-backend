package rabobankAPI.API.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.ServiceInterface.IProductService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService iProductService;

    public ProductController(@Qualifier("ProductService") IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("{id}")
    //GET at http://localhost:8082/product/id
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
        Product result = iProductService.getProductById(id);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        String e = "Product was not found.";
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);

    }

    @GetMapping
    //GET at http://localhost:8082/product
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = iProductService.getAllProducts();

        if (products != null) {
            return ResponseEntity.ok().body(products);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product result = iProductService.createProduct(productDTO);
        if (result != null) {
            String url = "product" + "/" + result.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
        String entity = "Product was not created. Please try again later.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    //DELETE at http://localhost:8082/product/3
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    //PUT at http://localhost:8082/product/
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (iProductService.updateProduct(product)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity("product was not updated", HttpStatus.NOT_FOUND);

    }
}