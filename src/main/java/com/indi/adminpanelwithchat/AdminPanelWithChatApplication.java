package com.indi.adminpanelwithchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.indi.adminpanelwithchat.controller.BootstrapController;

@SpringBootApplication
public class AdminPanelWithChatApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AdminPanelWithChatApplication.class, args);
		context.getBean(BootstrapController.class).setup();
	}

}
