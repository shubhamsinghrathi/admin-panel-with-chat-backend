package com.indi.adminpanelwithchat.model;

import java.util.Date;

import com.indi.adminpanelwithchat.entity.Admin;

import lombok.Data;

@Data
public class SafeAdmin {
	
	private int id;
	private int type;
	private String name;
	private String username;
	private Date createdAt;
	
	public SafeAdmin(Admin admin) {
		this.id = admin.getId();
		this.type = admin.getType();
		this.name = admin.getName();
		this.username = admin.getUsername();
		this.createdAt = admin.getCreatedAt();
	}
	
}
