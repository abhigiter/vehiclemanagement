/**
 * 
 */
package com.arg.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arg.dtos.ApiResponse;
import com.arg.entities.Car;
import com.arg.services.VehicleService;

/**
 * @author AbhijeetG
 *
 */

@RestController
@RequestMapping("/secure/")
public class VehicleController {
	
	@Autowired
	private VehicleService<Car> vehicleService;
	
	@PostMapping("/car")
	private ApiResponse<Car> save(HttpServletRequest request, @Valid @RequestBody Car car) {
		return vehicleService.save(car);	
	}
	
	@GetMapping("/car/{id}")
	public ApiResponse<Car> get(HttpServletRequest request, @PathVariable(value="id") String id) {
		return vehicleService.getById(id);
	}
	
	@PutMapping("/car/{id}")
	private ApiResponse<Car> update(HttpServletRequest request, @Valid @RequestBody Car car, @PathVariable(value="id") String id) {
		return vehicleService.update(car, id);
	}

	@DeleteMapping("/car/{id}")
	private ApiResponse<?> delete(HttpServletRequest request, @PathVariable(value="id") String id ) {
		return vehicleService.delete(id);
	}
	
	
}
