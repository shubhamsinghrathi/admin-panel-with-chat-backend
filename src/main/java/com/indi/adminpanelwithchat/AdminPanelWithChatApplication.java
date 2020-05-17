package com.indi.adminpanelwithchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.indi.adminpanelwithchat.controller.BootstrapController;

@SpringBootApplication
public class AdminPanelWithChatApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AdminPanelWithChatApplication.class, args);
		context.getBean(BootstrapController.class).setup();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",
						"HEAD");
			}
		};
	}

}
