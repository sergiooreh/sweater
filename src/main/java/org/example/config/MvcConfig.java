package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {                                    //set up view controllers to expose these templates(например log in)
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("greeting");
    }

                                        /*Stores registrations of resource handlers for serving static resources such as images, css files and others through Spring
                                        MVC including setting cache headers optimized for efficient loading in a web browser.*/

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/**")          //все запросы (на сервере) по пути img...
                    .addResourceLocations("file:///"+uploadPath+"/");       //будут перенаправлять по пути на комп (какие img нам показывать)
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/");                         //от корня проекта
    }
}
