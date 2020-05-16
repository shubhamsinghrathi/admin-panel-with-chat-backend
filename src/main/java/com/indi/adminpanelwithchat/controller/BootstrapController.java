package com.indi.adminpanelwithchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.indi.adminpanelwithchat.entity.Admin;
import com.indi.adminpanelwithchat.service.AdminService;

@Controller
public class BootstrapController {
	
	@Autowired
	private AdminService adminService;
	
	public void setup() {
		List<Admin> admins = adminService.getAdminList();
		if (admins.size() == 0) {
			Admin admin = new Admin();
			admin.setName("Admin Rathi");
			admin.setUsername("admin_rathi");
			String encodedPass = new BCryptPasswordEncoder().encode("12345678");
			admin.setPassword(encodedPass);
			adminService.addAdmin(admin);
		}
	}
	
}
