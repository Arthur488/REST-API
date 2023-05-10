package com.server_app.serverapp.products.Controller;


import com.server_app.serverapp.entity.Product;

import com.server_app.serverapp.products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsRestController {

    private final ProductsService productsService;

    @Autowired
    public ProductsRestController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public List <Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id) {
        Product product = productsService.findById(id);
        return product == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.ok(product);
    }

    @PostMapping ("/products")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productsService.saveProduct(product);
        return ResponseEntity.ok().body("Saved!");
    }

    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        productsService.saveProduct(product);
        return ResponseEntity.ok().body("Updated!");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Integer id) {
        productsService.deleteProductByID(id);
        return ResponseEntity.ok("Deleted!");
    }


}
