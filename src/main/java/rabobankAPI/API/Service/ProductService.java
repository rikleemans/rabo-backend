package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DALInterface.IProductDAL;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.ServiceInterface.IProductService;

import java.util.List;

@Service("ProductService")
public class ProductService implements IProductService {

    private final IProductDAL iProductDAL;
    private final convert convert;

    public ProductService(@Qualifier("ProductDAL") IProductDAL iProductDAL, @Qualifier("convert") convert convert) {
        this.iProductDAL = iProductDAL;
        this.convert = convert;
    }

    public Product getProductById(Long id) {
        try {
            return iProductDAL.getProductById(id);
        } catch (Exception e) {
            Product result = iProductDAL.getProductById(id);
            return result;
        }
    }

    public List<Product> getAllProducts() {
        try {
            return iProductDAL.getAllProducts();
        } catch (Exception e) {
            return null;
        }
    }

    public Product createProduct(ProductDTO productDTO) {
        try {
            //check user input not null
            if (productDTO.getLink() == null)
                return null;

            //convert dto to model
            Product product = convert.toProduct(productDTO);

            //save user info
            return iProductDAL.createProduct(product);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteProduct(Long id) {
        try {
            iProductDAL.deleteProduct(id);
        } catch (Exception e) {
        }
    }

    public boolean updateProduct(Product product) {
        try {
            //check user input not null
            if (product.getLink() == null)
                return false;

            //save user info
            iProductDAL.updateProduct(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
