package com.indi.adminpanelwithchat.service;

import java.util.List;

import com.indi.adminpanelwithchat.entity.Admin;

public interface AdminService {

	Admin addAdmin(Admin admin);
	Admin getAdminById(int id);
	Admin editAdmin(Admin admin);
	void deleteAdmin(int id);
	Admin getAdminByUsername(String username);
	List<Admin> getAdminList();
	
}
