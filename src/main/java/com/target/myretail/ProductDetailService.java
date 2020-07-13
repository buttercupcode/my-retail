package com.target.myretail;

import com.target.myretail.domain.CurrentPrice;
import com.target.myretail.domain.ProductDetail;
import com.target.myretail.domain.ProductJson;
import com.target.myretail.domain.ProductResponse;
import com.target.myretail.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductDetailService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${productDetail.url}")
    String url;

    @Autowired
    ProductRepository productRepository;

    public ProductDetail getProductDetail(int productId) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(this.url).buildAndExpand(productId);
        ResponseEntity<ProductJson> productDetail = restTemplate.getForEntity(uriComponents.toString(), ProductJson.class);
        return productDetail.getBody().getProductDetail();
    }

    public CurrentPrice getPrice(int productId) {
        return new CurrentPrice(12.50, "USD");

    }

    public ProductResponse updatePrice(ProductResponse productResponse, Double updatedPrice) {
        productResponse.getCurrentPrice().setPrice(updatedPrice);
        Product p = new Product();
        p.setId(productResponse.getId());
        p.setName(productResponse.getName());
        p.setPrice(productResponse.getCurrentPrice().getPrice());
        Product savedProduct = this.productRepository.save(p);
        return productResponse;
    }
}
