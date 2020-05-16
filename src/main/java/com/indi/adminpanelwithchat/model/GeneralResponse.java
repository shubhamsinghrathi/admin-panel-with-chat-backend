package com.indi.adminpanelwithchat.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse<T> {
	private int statusCode;
	private String message;
	private T data;
	private List<T> dataArr;
	
	public GeneralResponse(int statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	
	public GeneralResponse(int statusCode, String message, List<T> dataArr) {
		this.statusCode = statusCode;
		this.message = message;
		this.dataArr = dataArr;
	}
}
