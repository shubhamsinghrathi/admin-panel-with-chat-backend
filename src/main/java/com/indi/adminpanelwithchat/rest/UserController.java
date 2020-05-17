package com.indi.adminpanelwithchat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.adminpanelwithchat.entity.User;
import com.indi.adminpanelwithchat.model.GeneralResponse;
import com.indi.adminpanelwithchat.model.body.UserBody;
import com.indi.adminpanelwithchat.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("list")
	public ResponseEntity<GeneralResponse<User>> userList() {
		List<User> users = userService.getUserList();
		return new ResponseEntity<GeneralResponse<User>>(
				new GeneralResponse<User>(200, "Success", users), HttpStatus.OK);
	}

	@GetMapping("{userId}")
	public ResponseEntity<GeneralResponse<User>> getuser(@PathVariable("userId") int userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			return new ResponseEntity<GeneralResponse<User>>(new GeneralResponse<User>(404, "Not found"),
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<GeneralResponse<User>>(
					new GeneralResponse<User>(200, "Success", user), HttpStatus.OK);
		}
	}

	@DeleteMapping("{userId}")
	public ResponseEntity<GeneralResponse<String>> deleteuser(@PathVariable("userId") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<GeneralResponse<String>>(
				new GeneralResponse<String>(200, "Success", "User deleted successfully"), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<GeneralResponse<User>> adduser(@RequestBody UserBody userBody) {
		User user = new User();
		user.setName(userBody.getName());
		user.setUsername(userBody.getUsername());
		try {
			user = userService.addUser(user);
		} catch (Exception e) {
			return new ResponseEntity<GeneralResponse<User>>(
					new GeneralResponse<User>(400, "Username already exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<GeneralResponse<User>>(
				new GeneralResponse<User>(200, "Success", user), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<GeneralResponse<User>> edituser(@RequestBody UserBody userBody) {
		User user = userService.getUserById(userBody.getId());
		if (user == null) {
			return new ResponseEntity<GeneralResponse<User>>(new GeneralResponse<User>(404, "Not found"),
					HttpStatus.NOT_FOUND);
		}
		user.setName(userBody.getName());
		user = userService.editUser(user);

		return new ResponseEntity<GeneralResponse<User>>(
				new GeneralResponse<User>(200, "Success", user), HttpStatus.OK);
	}

}
