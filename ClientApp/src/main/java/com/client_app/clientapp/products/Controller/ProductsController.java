package com.client_app.clientapp.products.Controller;

import com.client_app.clientapp.entity.Product;
import com.client_app.clientapp.products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;

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

    @PostMapping("/update")
    public String updateProduct(Product product){
        productsService.updateProductViaAPI(product);
        return "redirect:/products";
    }

    @PostMapping("/save")
    public String saveProduct(Product product){
        productsService.saveProductViaAPI(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id){
        String response = productsService.deleteProductFromAPIByID(id);
        System.out.println(response);
        return "redirect:/products";
    }


}
