package com.target.myretail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRetailConfig {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();

    }

}

