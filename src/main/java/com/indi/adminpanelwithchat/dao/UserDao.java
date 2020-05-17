package com.indi.adminpanelwithchat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indi.adminpanelwithchat.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
