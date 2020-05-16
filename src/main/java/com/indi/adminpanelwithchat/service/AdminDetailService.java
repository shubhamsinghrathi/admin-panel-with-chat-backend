package com.indi.adminpanelwithchat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indi.adminpanelwithchat.entity.Admin;

@Service
public class AdminDetailService implements UserDetailsService {
	
	@Autowired
	private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminService.getAdminByUsername(username);
		if (admin == null) {
			throw new UsernameNotFoundException(username);
		}
		User user = new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
		return user;
	}

}
