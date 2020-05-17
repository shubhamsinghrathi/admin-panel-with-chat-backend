package com.indi.adminpanelwithchat.service;

import java.util.List;

import com.indi.adminpanelwithchat.entity.User;

public interface UserService {

	User addUser(User user);
	User getUserById(int id);
	User editUser(User user);
	void deleteUser(int id);
	List<User> getUserList();
	
}
