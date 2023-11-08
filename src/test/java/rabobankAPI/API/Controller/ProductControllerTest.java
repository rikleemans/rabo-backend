package rabobankAPI.API.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rabobankAPI.API.DALInterface.IProductDAL;
import rabobankAPI.API.DTO.ProductDTO;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.MockDAL.MockProductDAL;
import rabobankAPI.API.Model.Product;
import rabobankAPI.API.Service.ProductService;
import rabobankAPI.API.ServiceInterface.IProductService;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductControllerTest {

    private ProductController productController;

    @BeforeEach
    void setUp() {
        IProductDAL productDAL = new MockProductDAL();
        convert convert = new convert();
        IProductService productservice = new ProductService(productDAL, convert);
        this.productController = new ProductController(productservice);
    }

    @Test
    void getProductByIdHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Product expectedBody = new Product(1L, "youtube.com");

        //act
        ResponseEntity<?> responseEntity = productController.getProductById(1L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        Product resultBody = (Product) responseEntity.getBody();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
    }

    @Test
    void getProductByIdNegativeId() {
        //arrange
        HttpStatus expected = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = productController.getProductById(-5L);
        HttpStatus result = responseEntity.getStatusCode();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void getProductByIdZeroId() {
        //arrange
        HttpStatus expected = HttpStatus.NOT_FOUND;

        //act
        ResponseEntity<?> responseEntity = productController.getProductById(0L);
        HttpStatus result = responseEntity.getStatusCode();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void getAllProductsHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = productController.getAllProducts();
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        int resultSize = ((List<Product>) responseEntity.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createProductHappyFlow() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CREATED;
        URI expectedBody = URI.create("product/5");
        ProductDTO productDTO = new ProductDTO("spotify.com");
        int expectedSize = 5;

        //act
        ResponseEntity<?> responseEntity = productController.createProduct(productDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();
        URI resultBody = (URI) responseEntity.getBody();

        //Second act
        ResponseEntity<?> responseEntityList = productController.getAllProducts();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedBody, resultBody);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void createProductNullLink() {
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.CONFLICT;
        ProductDTO productDTO = new ProductDTO();
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = productController.createProduct(productDTO);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //Second act
        ResponseEntity<?> responseEntityList = productController.getAllProducts();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void deleteProductHappyFlow(){
        //keep in mind that there is only 1 outcome of this methode and that is HttpStatus.OK
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        int expectedSize = 3;

        //act
        ResponseEntity<?> responseEntity = productController.deleteProduct(4L);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //Second act
        ResponseEntity<?> responseEntityList = productController.getAllProducts();
        int resultSize = ((List<Product>) responseEntityList.getBody()).size();

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void updateProductHappyFlow(){
        //arrange
        HttpStatus expectedStatusCode = HttpStatus.NO_CONTENT;
        Product updateProduct = new Product(4L, "spotify.com");
        int expectedSize = 4;

        //act
        ResponseEntity<?> responseEntity = productController.updateProduct(updateProduct);
        HttpStatus resultStatusCode = responseEntity.getStatusCode();

        //Second act
        ResponseEntity<?> responseEntityList = productController.getAllProducts();
        List<Product> productList = (List<Product>) responseEntityList.getBody();
        int resultSize = productList.size();
        Product resultProduct = productList.get(resultSize-1);

        //assert
        assertEquals(expectedStatusCode, resultStatusCode);
        assertEquals(expectedSize, resultSize);
        assertEquals(updateProduct, resultProduct);
    }
}
