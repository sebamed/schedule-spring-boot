/**
 * 
 */
package com.mudri.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;
/*
  +---------------------------------------------+
  | Name: SwaggerConfiguration                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/*"), regex("/api/.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Schedule API")
				.description("API for organizing schedule")
				.termsOfServiceUrl("http://mudri.com")
				.contact(new Contact("Sebastian Dudas", "https://github.com/sebamed/schedule-spring-boot", "seba.med@yahoo.como")).license("Mudri odobrio")
				.licenseUrl("seba.med@yahoo.com").version("1.0").build();
	}

}
