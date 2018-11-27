package com.example.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

public class SwaggerConfig {
	
	@Bean
	public Docket productApi(){
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.select().apis(RequestHandlerSelectors.basePackage("com.example.demo")).paths(PathSelectors.any()).build().apiInfo(metaData());;
		
		return docket;
		
	}
	
	
	private ApiInfo metaData() {
	   @SuppressWarnings("deprecation")
	ApiInfo apiInfo = new ApiInfo("Item MicroService", "Item Microservice manages Item related services",
	   	"1.1", "Terms of service", 	"License version 2.0", "http://licensesURL/", "vendorExtensions"); 
	   
	   	return apiInfo;	    
	}
	

}
