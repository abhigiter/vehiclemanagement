/**
 * 
 */
package com.arg.utils;

/**
 * @author AbhijeetG
 *
 */
public enum ExceptionCodes {
		  
	  CARNOTFOUND(404, "Car Object not found for given id"),
	  CARARGUMENTNOTVALID(400, "Required fields are missing in object");

	  private final int id;
	  private final String message;

	  ExceptionCodes(int id, String message) {
	     this.id = id;
	     this.message = message;
	  }

	  public int getId() { return id; }
	  public String getMessage() { return message; }


}
