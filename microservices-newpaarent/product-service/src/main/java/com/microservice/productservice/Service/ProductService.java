package com.microservice.productservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.Repository.ProductRepository;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
  @Autowired
    private ObjectMapper objectMapper;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).
                          description(productRequest.getDescription()).
                          price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("product {} is saved", product.getId());

    }

    public List<ProductResponse> getAllProducts() {
       List<Product> products =  productRepository.findAll();
      return products.stream().map(this::mapToProductResponse).toList();
       //products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }

    public ProductResponse getProductById(String id) throws JsonProcessingException {
       Optional<Product> product =productRepository.findById(id);




//       String produc = objectMapper.writeValueAsString(product);
        ProductResponse productRe = ProductResponse.builder().id(product.get().getId()).name(product.get().getName()).description(product.get().getDescription()).price(product.get().getPrice()).build();
       return productRe;

    }


}
