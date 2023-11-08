package rabobankAPI.API.DAL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.IProductDAL;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Repository.IProductRepo;

import java.util.List;

@Repository("ProductDAL")
public class ProductDAL implements IProductDAL {

    private final IProductRepo iProductRepo;

    public ProductDAL(@Qualifier("ProductRepo") IProductRepo iProductRepo) {
        this.iProductRepo = iProductRepo;
    }

    public Product getProductById(Long id) {
        return iProductRepo.getById(id);
    }

    public List<Product> getAllProducts() {
        return iProductRepo.findAll();
    }

    public Product createProduct(Product product) {
        return iProductRepo.save(product);
    }

    public void deleteProduct(Long id) {
    }

    public boolean updateProduct(Product product) {
        iProductRepo.save(product);
        return true;
    }
}
