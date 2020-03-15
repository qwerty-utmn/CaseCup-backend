package org.example

import io.swagger.annotations.Api
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration::class)
class SpringFoxConfig: WebMvcConfigurer {
    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api::class.java))//RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
            .paths(PathSelectors.any())
            .build()
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}

//    fun productApi(): Docket = Docket(DocumentationType.SWAGGER_2).select()
//        .apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers")).paths(regex("/product.*")).build()
//

