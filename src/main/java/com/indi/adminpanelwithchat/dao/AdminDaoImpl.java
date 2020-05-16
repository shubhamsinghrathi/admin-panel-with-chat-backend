package com.indi.adminpanelwithchat.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.adminpanelwithchat.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Admin addAdmin(Admin admin) {
		entityManager.persist(admin);
		return admin;
	}

	@Override
	public Admin getAdminById(int id) {
		return entityManager.find(Admin.class, id);
	}

	@Override
	public Admin editAdmin(Admin admin) {
		entityManager.merge(admin);
		return admin;
	}

	@Override
	public void deleteAdmin(int id) {
		Query query = entityManager.createQuery("delete from Admin where id=:adminId");
		query.setParameter("adminId", id);
		query.executeUpdate();
	}

	@Override
	public Admin getAdminByUsername(String username) {
		Query query = entityManager.createQuery("from Admin where username=:username", Admin.class);
		query.setParameter("username", username);
		return (Admin) query.getSingleResult();
	}
	
	@Override
	public List<Admin> getAdminList() {
		Query query = entityManager.createQuery("from Admin", Admin.class);
		@SuppressWarnings("unchecked")
		List<Admin> admins = query.getResultList();
		return admins;
	}

}
