package com.microservice.productservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.productservice.Service.ProductService;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();

    }
    @GetMapping("/{id}")
    public ProductResponse getProductsById(String id) throws JsonProcessingException {
        return productService.getProductById(id);
    }
}
