/**
 * 
 */
package com.arg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AbhijeetG
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found for given Id")
public class CarNotFoundException extends RuntimeException {
}