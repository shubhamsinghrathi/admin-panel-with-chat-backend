package com.indi.adminpanelwithchat.rest;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.adminpanelwithchat.model.GeneralResponse;
import com.indi.adminpanelwithchat.model.Test;
import com.indi.adminpanelwithchat.model.body.Login;
import com.indi.adminpanelwithchat.service.AdminDetailService;
import com.indi.adminpanelwithchat.service.AdminService;
import com.indi.adminpanelwithchat.util.JwtUtils;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminDetailService adminDetailService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;

	@GetMapping("test")
	public ResponseEntity<GeneralResponse<Test>> testData() {
		Test test = new Test(55, "Testing some data");
		LinkedList<Test> testArr = new LinkedList<Test>();
		testArr.add(test);
		GeneralResponse<Test> gr = new GeneralResponse<Test>(200, "test message", test, testArr);
		return new ResponseEntity<GeneralResponse<Test>>(gr, HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<GeneralResponse<String>> login(@RequestBody Login loginBody) {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword())
			);
		} catch (Exception exc) {
			return new ResponseEntity<GeneralResponse<String>>(new GeneralResponse<String>(401, "Invalid credentials provided", "Invalid username or password"), HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = adminDetailService.loadUserByUsername(loginBody.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return new ResponseEntity<GeneralResponse<String>>(new GeneralResponse<String>(200, "Login Successful", jwt), HttpStatus.OK);
	}
	
}
