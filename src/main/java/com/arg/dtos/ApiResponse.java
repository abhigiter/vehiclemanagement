/**
 * 
 */
package com.arg.dtos;

/**
 * @author AbhijeetG
 * 
 * not adding lombok as I am nor sure about the target environment
 */



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiResponse<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private boolean status;
	private E result;
	private E data;
	private List<String> errorArguments = new ArrayList<>();

	public ApiResponse() {
		
	}
	
	public ApiResponse(String message) {
		this.message = message;
	}
	
	public ApiResponse(boolean status) {
		this.status = status;
	}
	
	public ApiResponse(String message, boolean status) {
		this.message = message;
		this.status = status;
	}

	public ApiResponse(String message, boolean status, List<String> errorArguments) {
		this.message = message;
		this.status = status;
		this.errorArguments = errorArguments;
	}
	
	public ApiResponse(boolean status, E result) {
		this.status = status;
		this.result = result;
	}
	
	public ApiResponse(String message, boolean status, E result) {
		this.message = message;
		this.status = status;
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public E getResult() {
		return result;
	}

	public void setResult(E result) {
		this.result = result;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public List<String> getErrorArguments() {
		return errorArguments;
	}

	public void setErrorArguments(List<String> errorArguments) {
		this.errorArguments = errorArguments;
	}
}
