package com.indi.adminpanelwithchat.rest;

import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.adminpanelwithchat.model.GeneralResponse;
import com.indi.adminpanelwithchat.model.Test;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@GetMapping("test")
	public ResponseEntity<GeneralResponse<Test>> testData() {
		Test test = new Test(55, "Testing some data");
		LinkedList<Test> testArr = new LinkedList<Test>();
		testArr.add(test);
		GeneralResponse<Test> gr = new GeneralResponse<Test>(200, "test message", test, testArr);
		return new ResponseEntity<GeneralResponse<Test>>(gr, HttpStatus.OK);
	}
	
}
