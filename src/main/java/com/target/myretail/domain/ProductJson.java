package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductJson {
    @JsonProperty("product")
    private ProductDetail productDetail;

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
