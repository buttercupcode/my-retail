package com.target.myretail;

import com.target.myretail.domain.CurrentPrice;
import com.target.myretail.domain.ProductDetail;
import com.target.myretail.domain.ProductJson;
import com.target.myretail.domain.ProductResponse;
import com.target.myretail.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ProductDetailServiceTest {

    private final BeanHelper beanHelper = new BeanHelper();

    @Autowired
    private ProductDetailService productDetailService;
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        ResponseEntity<ProductJson> productJsonDetail = new ResponseEntity<>(beanHelper.getTestProductJson(), HttpStatus.OK);
        Mockito.when(productRepository.save(any(Product.class)))
                .thenReturn(beanHelper.getTestProduct());
        ReflectionTestUtils.setField(productDetailService, "url", "http://dummyURl.com");
        Mockito.when(restTemplate.getForEntity("http://dummyURl.com", ProductJson.class)).thenReturn(productJsonDetail);
    }

    public ProductResponse prepareTestProduct() {
        CurrentPrice currentPrice = new CurrentPrice(11, "USD");
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(1);
        productResponse.setName("Dummy product");
        productResponse.setCurrentPrice(currentPrice);
        return productResponse;
    }

    @Test
    public void whenPriceUpdate_thenReturnResponse() {
        ProductResponse testProduct = prepareTestProduct();
        ProductResponse productResponse = productDetailService.updatePrice(testProduct, 12.50);
        assertEquals(productResponse.getCurrentPrice().getPrice(), 12.50);
    }

    @Test
    public void whenExternalInvocation_thenReturnResponse() {
        ProductResponse testProduct = prepareTestProduct();
        ProductDetail productDetail = productDetailService.getProductDetail(testProduct.getId());
        assertNotNull(productDetail);
    }

    @TestConfiguration
    static class ProductDetailServiceContextConfiguration {

        @Bean
        public ProductDetailService productDetailService() {
            return new ProductDetailService();
        }

        @Bean
        public RestTemplate testRestTemplate() {
            return new RestTemplate();
        }
    }
}
