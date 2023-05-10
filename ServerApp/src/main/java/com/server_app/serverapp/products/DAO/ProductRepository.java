package com.server_app.serverapp.products.DAO;

import com.server_app.serverapp.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
