package org.tommy.mongofirstdemo.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("My Teacher product APIs")
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("org.tommy.mongofirstdemo"))
        .build()
        .apiInfo(new ApiInfo("My-teacher project", "A fancy product to find out your personal teacher.",
            "1.0-SNAPSHOT", "SID",
            "tomaslingotti@gmail.com", "MIT", "https://www.debian.org/licence"));
  }
}
