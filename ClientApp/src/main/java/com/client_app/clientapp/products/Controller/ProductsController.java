package com.client_app.clientapp.products.Controller;

import com.client_app.clientapp.entity.HttpMethods;
import com.client_app.clientapp.entity.Product;
import com.client_app.clientapp.products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String listAllProducts(Model model){
        List <Product> allProductsFromAPI = productsService.getAllProductsFromAPI();
        model.addAttribute("allProductList", allProductsFromAPI);
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable(name = "id") Integer id, Model model){
        Product product = productsService.getProductFromAPIByID(id);
        model.addAttribute("product", product);
        return "fragments::modal_edit_content";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "fragments::modal_edit_content";
    }

    @PostMapping("/save_update")
    public String saveProduct(Product product) throws IOException {
        HttpMethods method = product.getId() == null ? HttpMethods.POST : HttpMethods.PUT;
        productsService.saveProductViaAPI(product, method);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) throws IOException {
        productsService.deleteProductFromAPIByID(id);
        return "redirect:/products";
    }


}
