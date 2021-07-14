/**
 * 
 */
package com.arg.advices;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arg.dtos.ApiResponse;
import com.arg.exceptions.CarNotFoundException;
import com.arg.utils.ExceptionCodes;


/**
 * @author AbhijeetG
 *
 */
@ControllerAdvice
public class VehicleControllerAdvice extends ResponseEntityExceptionHandler {
	
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)  // 404
	  @ExceptionHandler(value = {CarNotFoundException.class})
	  @ResponseBody
	  public ApiResponse<?> resourceNotFoundException(HttpServletRequest req, CarNotFoundException e) {
	   
		 ApiResponse<?> response = new ApiResponse<>();
	     response.setStatus(false);
	     response.setMessage(ExceptionCodes.CARNOTFOUND.getMessage());
	    return response;
	  }
	  
	  @Override
	  @ResponseBody
	  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
              HttpHeaders headers, HttpStatus status, WebRequest request) {
          List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
          ApiResponse<?> response = new ApiResponse<>();
 	     response.setStatus(false);
 	     response.setErrorArguments(validationList);
          return new ResponseEntity<>(response, status);
	  }
}
