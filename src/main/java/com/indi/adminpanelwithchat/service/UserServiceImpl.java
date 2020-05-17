package com.indi.adminpanelwithchat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indi.adminpanelwithchat.dao.UserDao;
import com.indi.adminpanelwithchat.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		user = userDao.save(user);
		return user;
	}

	@Override
	public User getUserById(int id) {
		Optional<User> user = userDao.findById(id);
		if (user.isPresent()) return user.get();
		else return null;
	}

	@Override
	public User editUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(int id) {
		try {
			userDao.deleteById(id);	
		} catch (Exception e) { }
	}

	@Override
	public List<User> getUserList() {
		return userDao.findAll();
	}

}
