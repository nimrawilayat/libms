package com.hurontg.libms.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "com.hurontg.libms" }, 
	excludeFilters = {
			@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
			@Filter(type = FilterType.ANNOTATION, value = Configuration.class),
			@Filter(type = FilterType.ANNOTATION, value = Controller.class)
	}
)
@PropertySources({
    @PropertySource(value = "classpath:application.properties"),
    @PropertySource(value = "file:${user.home}/next-platform.properties", ignoreResourceNotFound = true)    
})
public class RootConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
}