package com.progressivecoder.productapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}

@RestController
@RefreshScope
class MessageController{

	@Value("${msg: Config server not working}")
	private String msg;

	@GetMapping(value = "/msg")
	String getMsg(){
		return this.msg;
	}

}

@RestController
class ProductController{

	@GetMapping(value = "/products")
	public Product getProduct(){
		return new Product("Laptop", "The best laptop in the world");
	}

}

class Product{

	String productName;

	String productDescription;

	public Product(String productName, String productDescription) {
		this.productName = productName;
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}
