package rabobankAPI.API.DALInterface;

import rabobankAPI.API.Model.Product;

import java.util.List;

public interface IProductDAL {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    public Product createProduct(Product product);
    public void deleteProduct(Long id);
    public boolean updateProduct(Product product);
}
