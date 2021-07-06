package com.app.myapp.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EntityScan(basePackages = { "com.app.myapp.model" })
@ComponentScan(basePackages = {
    "com.app.myapp.resource",
    "com.app.myapp.service"
})
public class CustomWebConfiguration implements WebMvcConfigurer {
}
