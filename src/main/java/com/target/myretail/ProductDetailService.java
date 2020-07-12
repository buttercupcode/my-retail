package com.target.myretail;

import com.target.myretail.domain.CurrentPrice;
import com.target.myretail.domain.ProductDetail;
import com.target.myretail.domain.ProductJson;
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

    public ProductDetail getProductDetail(int productId) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(this.url).buildAndExpand(productId);
        ResponseEntity<ProductJson> productDetail = restTemplate.getForEntity(uriComponents.toString(), ProductJson.class);
        return productDetail.getBody().getProductDetail();
    }

    public CurrentPrice getPrice(int productId) {
        return new CurrentPrice(12.50, "USD");

    }
}
