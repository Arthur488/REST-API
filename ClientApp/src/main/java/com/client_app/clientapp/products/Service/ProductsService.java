package com.client_app.clientapp.products.Service;

import com.client_app.clientapp.entity.Product;

import java.io.IOException;
import java.util.List;

/**
 * The interface Products service.
 */
public interface ProductsService {

    /**
     * Gets all products from api.
     *
     * @return the all products from api
     */
    List<Product> getAllProductsFromAPI();

    /**
     * Gets product from api by id.
     *
     * @param id the id
     * @return the product from api by id
     */
    Product getProductFromAPIByID(Integer id);

    /**
     * Update product via api string.
     *
     * @param product the product
     * @return the string
     */
    String updateProductViaAPI(Product product);

    /**
     * Delete product from api by id string.
     *
     * @param id the id
     * @return the string
     */
    String deleteProductFromAPIByID(Integer id);

    String saveProductViaAPI(Product product);
}
