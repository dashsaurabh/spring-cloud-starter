package com.progressivecoder.productsconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callProductApplication_Fallback")
    public String callProductApplication(){

        System.out.println("Fetching Product Information");

        String response = restTemplate
                .exchange("http://localhost:8085/product-application/products"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }
                        , "").getBody();

        System.out.println("Response Received from Product Application");

        return "NORMAL CALL Successful" + "Product Details:  " + response;

    }

    @SuppressWarnings("unused")
    private String callProductApplication_Fallback(){

        System.out.println("Product Application is down! Fallback enabled!");

        return "CIRCUIT BREAKER ENABLED!! No response from Product Application at this time";

    }

}
