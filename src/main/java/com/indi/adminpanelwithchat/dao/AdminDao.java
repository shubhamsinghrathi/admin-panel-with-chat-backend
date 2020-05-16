package com.indi.adminpanelwithchat.dao;

import java.util.List;

import com.indi.adminpanelwithchat.entity.Admin;

public interface AdminDao {
	
	Admin addAdmin(Admin admin);
	Admin getAdminById(int id);
	Admin editAdmin(Admin admin);
	void deleteAdmin(int id);
	Admin getAdminByUsername(String username);
	List<Admin> getAdminList();
	
}
