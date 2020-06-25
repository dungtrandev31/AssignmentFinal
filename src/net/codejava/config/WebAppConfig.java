package net.codejava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter{

	   @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
	       registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	       registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	       registry.addResourceHandler("/mp4/**").addResourceLocations("/img/").setCachePeriod(31556926);
	       
	   }
}
