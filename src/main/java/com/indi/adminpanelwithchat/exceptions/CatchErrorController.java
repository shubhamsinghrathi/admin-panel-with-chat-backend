package com.indi.adminpanelwithchat.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.indi.adminpanelwithchat.model.GeneralResponse;
import com.indi.adminpanelwithchat.model.SafeAdmin;

@ControllerAdvice
public class CatchErrorController {

	@ExceptionHandler(value = InvalidCredentials.class)
	public ResponseEntity<GeneralResponse<SafeAdmin>> invalidCredentialsProvided(HttpServletRequest req, Exception e) {
		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GeneralResponse<SafeAdmin>> errorOccured(HttpServletRequest req, Exception e) {
		return new ResponseEntity<GeneralResponse<SafeAdmin>>(
				new GeneralResponse<SafeAdmin>(500, "Some error occured"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
