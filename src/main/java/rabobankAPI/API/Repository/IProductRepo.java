package rabobankAPI.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.Model.Product;

@Repository("ProductRepo")
public interface IProductRepo extends JpaRepository<Product, Long> {
}
