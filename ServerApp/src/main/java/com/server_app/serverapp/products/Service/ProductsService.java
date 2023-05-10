package com.server_app.serverapp.products.Service;

import com.server_app.serverapp.entity.Product;

import java.util.List;

/**
 * The interface Products service.
 */
public interface ProductsService {

    /**
     * Find all products form database.
     *
     * @return the list <Product>
     */
    List<Product> findAll();

    /**
     * Find by id product.
     *
     * @param productId the product id
     * @return the product
     */
    Product findById(Integer productId);

    /**
     * Save product.
     *
     * @param product the product
     */
    void saveProduct(Product product);

    /**
     * Delete product by id.
     *
     * @param id the id
     */
    void deleteProductByID(Integer id);

}
