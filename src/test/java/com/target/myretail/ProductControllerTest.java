package com.target.myretail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    private final BeanHelper beanHelper = new BeanHelper();
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDetailService service;

    @BeforeEach
    public void setUp() {
        Mockito.when(service.getProductDetail(123)).thenReturn(beanHelper.getTestProductDetail());
        Mockito.when(service.getPrice(123)).thenReturn(beanHelper.getTestCurrentPrice());
    }

    @Test
    public void testGetProducts_whenSuccessfulProdReturned() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/products/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(Map.class)))
                .andReturn();

    }
}