package rabobankAPI.API.MockDAL;

import rabobankAPI.API.DALInterface.IProductDAL;
import rabobankAPI.API.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MockProductDAL implements IProductDAL {
    private List<Product> products;

    public MockProductDAL() {
        this.products = new ArrayList<>();
        products.add(new Product(1L, "youtube.com", "http://link.com", "name1"));
        products.add(new Product(2L, "google.com","http://link.com", "name1"));
        products.add(new Product(3L, "facebook.com","http://link.com", "name1"));
        products.add(new Product(4L, "twitter.com","http://link.com", "name1"));
    }

    public Product getProductById(Long id) {
        for(Product product: products){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product createProduct(Product product) {
        Long id = products.get(products.size()-1).getId()+1;
        product.setId(id);
        products.add(product);
        return product;
    }

    public void deleteProduct(Long id) {
        int indexOfProduct = 0;
        for(Product product : products) {
            if (product.getId().equals(id)){
                continue;
            }
            indexOfProduct++;
        }
        if(indexOfProduct == products.size()){
            return;
        }
        products.remove(indexOfProduct);
    }

    public boolean updateProduct(Product updateProduct) {
        int indexOfProduct = 0;
        for(Product product : products) {
            if (product.getId().equals(updateProduct.getId())){
                continue;
            }
            indexOfProduct++;
        }
        if (indexOfProduct == products.size()){
            return false;
        }
        products.set(indexOfProduct, updateProduct);
        return true;
    }
}
