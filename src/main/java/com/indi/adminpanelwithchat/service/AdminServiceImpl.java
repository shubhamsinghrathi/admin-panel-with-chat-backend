package com.indi.adminpanelwithchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.adminpanelwithchat.dao.AdminDao;
import com.indi.adminpanelwithchat.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional
	public Admin addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

	@Override
	@Transactional
	public Admin getAdminById(int id) {
		return adminDao.getAdminById(id);
	}

	@Override
	@Transactional
	public Admin editAdmin(Admin admin) {
		return adminDao.editAdmin(admin);
	}

	@Override
	@Transactional
	public void deleteAdmin(int id) {
		adminDao.deleteAdmin(id);
	}

	@Override
	@Transactional
	public Admin getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}

	@Override
	@Transactional
	public List<Admin> getAdminList() {
		return adminDao.getAdminList();
	}

}
