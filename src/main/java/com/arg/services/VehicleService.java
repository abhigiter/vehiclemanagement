/**
 * 
 */
package com.arg.services;

import java.util.List;

import com.arg.dtos.ApiResponse;

/**
 * @author AbhijeetG
 *
 */
public interface VehicleService<T> {

	ApiResponse<T> save(T t);
	ApiResponse<T> update(T t, String id);
	ApiResponse<T> delete(String id);
	ApiResponse<T> getById(String id);
	ApiResponse<List<T>> getAll();
	
}
