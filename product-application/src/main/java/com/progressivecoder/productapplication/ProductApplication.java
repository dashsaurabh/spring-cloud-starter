package com.progressivecoder.productapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
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
