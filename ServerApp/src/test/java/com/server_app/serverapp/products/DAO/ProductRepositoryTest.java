package test.java.com.server_app.serverapp.products.DAO;


import main.java.com.server_app.serverapp.entity.Product;
import main.java.com.server_app.serverapp.products.DAO.ProductRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreateAndSaveProductInDB() {
        Product product = new Product("Laptop", 15000d, "Cool laptop");
        productRepository.save(product);
    }

    @Test
    public void testSaveManyProductsInDB() {
        Product product0 = new Product("Laptop", 15000d, "Cool laptop");
        Product product1 = new Product("Phone", 560.99, "Iphone 14Pro");
        Product product2 = new Product("Phone", 1000d, "Iphone 12");
        Product product3 = new Product("Phone", 899.59, "Xiaomi One Plus");
        Product product4 = new Product("TV", 320d, "LG FHD");
        Product product5 = new Product("TV", 800d, "Samsung 4k");
        List <Product> products = List.of(product0, product1, product2, product3, product4, product5);
        productRepository.saveAll(products);
    }

    @Test
    public void testUpdateProductFromDB(){
        int productId = 1;
        Product productByIdToEdit = productRepository.findById(productId).get();
        productByIdToEdit.setPrice(659d);
        productByIdToEdit.setDescription("Apple MacBook Pro 16 1 TB");
    }

    @Test
    public void testGetProductByID() {
        int productsIdToBeGetFromDB = 1;

        Optional <Product> optionalProduct = productRepository.findById(productsIdToBeGetFromDB);

        String result = optionalProduct.isPresent() ? String.valueOf(optionalProduct.get()) : "optionalProduct is empty!";
        System.out.println(result);
    }

    @Test
    public void testDeleteProductById(){
        int productsIdToBeDeletedFromDB = 6;

        productRepository.deleteById(productsIdToBeDeletedFromDB);

        Optional <Product> byId = productRepository.findById(productsIdToBeDeletedFromDB);

    }

}