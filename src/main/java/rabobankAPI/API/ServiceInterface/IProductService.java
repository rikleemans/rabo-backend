package rabobankAPI.API.ServiceInterface;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Model.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    boolean updateProduct(Product product);
}
