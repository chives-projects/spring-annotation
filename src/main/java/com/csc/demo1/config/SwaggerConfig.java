package com.csc.demo1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description Swagger配置
 * @Package
 * @Author csc
 * @Date 2019/08/12 16:00
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    //    @Value(value = "${swagger.enabled}")
    boolean swaggerEnabled=true;

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .enable(swaggerEnabled)
//                .pathProvider(pathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.csc.demo1"))
                .paths(PathSelectors.any())
                .build();
    }

    private PathProvider pathProvider(){
        return new PathProvider() {
            @Override
            public String getApplicationBasePath() {
                return "/demoApi";
            }

            @Override
            public String getOperationPath(String operationPath) {
                return operationPath;
            }

            @Override
            public String getResourceListingPath(String groupName, String apiDeclaration) {
                return null;
            }
        };
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .description("xxx接口")
                .contact(new Contact("csc", "http://xx.xx.xx.xx/csc/api/com.xx.git", "xxx@xxx.com"))
                .version("v1.0.0")
                .title("API文档")
                .license("Apache2.0")
                .licenseUrl("http://www,apache.org/licenses/LICENSE-2.0")
                .build();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}