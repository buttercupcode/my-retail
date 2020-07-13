package com.target.myretail;

import com.target.myretail.domain.CurrentPrice;
import com.target.myretail.domain.ProductDetail;
import com.target.myretail.domain.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.BiFunction;

@RestController
public class ProductController {
    public static BiFunction<ProductDetail, CurrentPrice, ProductResponse> PROD_MAPPER = ((productDetail, currentPrice) -> {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCurrentPrice(currentPrice);
        productResponse.setName(productDetail.getItem().getProductDescription().getTitle());
        return productResponse;
    });
    @Autowired
    ProductDetailService productDetailService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable("id") Integer productId) {

        ProductDetail prodDetail = productDetailService.getProductDetail(productId);

        CurrentPrice price = productDetailService.getPrice(productId);
        ProductResponse p = PROD_MAPPER.apply(prodDetail, price);
        p.setId(productId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProducts(@PathVariable("id") Integer productId, @RequestBody Double newPrice) {

        ProductDetail prodDetail = productDetailService.getProductDetail(productId);

        CurrentPrice price = productDetailService.getPrice(productId);
        ProductResponse p = PROD_MAPPER.apply(prodDetail, price);
        productDetailService.updatePrice(p, newPrice);
        p.setId(productId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Hello My Retail", HttpStatus.OK);
    }
}
