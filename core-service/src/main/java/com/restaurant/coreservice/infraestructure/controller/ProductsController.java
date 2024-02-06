package com.restaurant.coreservice.infraestructure.controller;

import com.restaurant.coreservice.aggregates.constant.Constants;
import com.restaurant.coreservice.aggregates.constant.HttpStatus;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.application.service.ProductService;
import com.restaurant.coreservice.domain.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/core/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseBase getAll(){
        return productService.getProducts();
    }
    @GetMapping("/actives")
    public ResponseBase getAllActives(){
        return productService.getActivesProducts();
    }

    @GetMapping("/{productId}")
    public ResponseBase getProduct(@PathVariable Integer productId){
        return productService.getProduct(productId);
    }

    @PostMapping()
    public ResponseBase createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public ResponseBase updateProduct(@PathVariable Integer productId, @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("/{productId}")
    public ResponseBase deleteProduct(@PathVariable Integer productId){
        return productService.deleteProduct(productId);
    }
}
