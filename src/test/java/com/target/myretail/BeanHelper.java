package com.target.myretail;

import com.target.myretail.domain.*;
import com.target.myretail.entity.Product;

public class BeanHelper {

    public Product getTestProduct() {
        Product testProduct = new Product();
        testProduct.setId(1);
        testProduct.setName("Dummy product");
        testProduct.setPrice(12.50);
        return testProduct;
    }

    public ProductDescription getTestProductDescription() {
        ProductDescription pd = new ProductDescription();
        pd.setTitle("Michael Angelo");
        return pd;
    }

    public Item getTestItem() {
        Item item = new Item();
        item.setProductDescription(getTestProductDescription());
        return item;
    }

    public ProductDetail getTestProductDetail() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setItem(getTestItem());
        return productDetail;
    }

    public ProductJson getTestProductJson() {
        ProductJson productJson = new ProductJson();
        productJson.setProductDetail(getTestProductDetail());
        return productJson;
    }

    public CurrentPrice getTestCurrentPrice() {
        return new CurrentPrice(12.50, "USD");
    }
}
