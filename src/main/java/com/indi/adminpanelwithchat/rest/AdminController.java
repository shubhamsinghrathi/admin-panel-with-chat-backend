package com.indi.adminpanelwithchat.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.adminpanelwithchat.entity.Admin;
import com.indi.adminpanelwithchat.exceptions.InvalidCredentials;
import com.indi.adminpanelwithchat.model.GeneralResponse;
import com.indi.adminpanelwithchat.model.SafeAdmin;
import com.indi.adminpanelwithchat.model.body.AdminBody;
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

	@PostMapping("login")
	public ResponseEntity<GeneralResponse<SafeAdmin>> login(@RequestBody Login loginBody) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
		} catch (Exception exc) {
//			return new ResponseEntity<GeneralResponse<SafeAdmin>>(
//					new GeneralResponse<SafeAdmin>(401, "Invalid credentials provided"), HttpStatus.UNAUTHORIZED);
			throw new InvalidCredentials("Invalid credentials were provided");
		}

		final UserDetails userDetails = adminDetailService.loadUserByUsername(loginBody.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Admin admin = adminService.getAdminByUsername(userDetails.getUsername());

		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(200, jwt, new SafeAdmin(admin)), HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<GeneralResponse<SafeAdmin>> adminList() {
		List<Admin> admins = adminService.getAdminList();
		List<SafeAdmin> safeAdmins = new LinkedList<SafeAdmin>();
		admins.forEach(admin -> {
			safeAdmins.add(new SafeAdmin(admin));
		});
		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(200, "Success", safeAdmins), HttpStatus.OK);
	}

	@GetMapping("{adminId}")
	public ResponseEntity<GeneralResponse<SafeAdmin>> getAdmin(@PathVariable("adminId") int adminId) {
		Admin admin = adminService.getAdminById(adminId);
		if (admin == null) {
			return new ResponseEntity<GeneralResponse<SafeAdmin>>(
					new GeneralResponse<SafeAdmin>(404, "Not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<GeneralResponse<SafeAdmin>>(
					new GeneralResponse<SafeAdmin>(200, "Success", new SafeAdmin(admin)), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("{adminId}")
	public ResponseEntity<GeneralResponse<String>> deleteAdmin(@PathVariable("adminId") int adminId) {
		adminService.deleteAdmin(adminId);
		return new ResponseEntity<GeneralResponse<String>>(
				new GeneralResponse<String>(200, "Success", "Admin deleted successfully"), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<GeneralResponse<SafeAdmin>> addAdmin(@RequestBody AdminBody adminBody) {
		Admin admin = new Admin();
		admin.setName(adminBody.getName());
		admin.setUsername(adminBody.getUsername());
		admin.setType(adminBody.getType());
		admin.setPassword(new BCryptPasswordEncoder().encode(adminBody.getPassword()));
		try {
			admin = adminService.addAdmin(admin);
		} catch (Exception e) {
			return new ResponseEntity<GeneralResponse<SafeAdmin>>(
					new GeneralResponse<SafeAdmin>(400, "Username already exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(200, "Success", new SafeAdmin(admin)), HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<GeneralResponse<SafeAdmin>> editAdmin(@RequestBody AdminBody adminBody) {		
		Admin admin = adminService.getAdminById(adminBody.getId());
		if (admin == null) {
			return new ResponseEntity<GeneralResponse<SafeAdmin>>(
					new GeneralResponse<SafeAdmin>(404, "Not found"), HttpStatus.NOT_FOUND);
		}
		admin.setName(adminBody.getName());
		admin = adminService.editAdmin(admin);
		
		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(200, "Success", new SafeAdmin(admin)), HttpStatus.OK);
	}

}
