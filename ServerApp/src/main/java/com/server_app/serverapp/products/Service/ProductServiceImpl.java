package com.server_app.serverapp.products.Service;

import com.server_app.serverapp.entity.Product;

import com.server_app.serverapp.products.DAO.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List <Product> findAll() {
        return (List <Product>) productRepository.findAll();
    }

    @Override
    public Product findById(Integer productId) {
        return productRepository.findById(productId).isPresent() ? productRepository.findById(productId).get() : null;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductByID(Integer id) {
        productRepository.deleteById(id);
    }

}
