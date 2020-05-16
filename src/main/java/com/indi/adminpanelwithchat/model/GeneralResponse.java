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
}
