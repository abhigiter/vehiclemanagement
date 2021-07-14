/**
 * 
 */
package com.arg.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arg.dtos.ApiResponse;
import com.arg.entities.Car;
import com.arg.exceptions.CarNotFoundException;
import com.arg.repository.VehicleRepository;
import com.arg.services.VehicleService;

/**
 * @author AbhijeetG
 *
 */
@Service
public class VehicleServiceImpl implements VehicleService<Car> {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	DatamuseService datamuseService;

	@Override
	public ApiResponse<Car> save(Car car) {
		car = vehicleRepository.save(car);
		return new ApiResponse<>("Created", true, car);
	}

	@Override
	public ApiResponse<Car> update(Car car, String id) {
		Car cartoSave = vehicleRepository.findById(id).orElseThrow(() -> new CarNotFoundException());
		cartoSave.setColour(car.getColour());
		cartoSave.setMake(car.getMake());
		cartoSave.setModel(car.getModel());
		cartoSave.setYear(car.getYear());
		car = vehicleRepository.save(cartoSave);
		return new ApiResponse<>("Updated", true, car);
	}

	@Override
	public ApiResponse delete(String id) {
		Car car = vehicleRepository.findById(id).orElseThrow(() -> new CarNotFoundException());
		vehicleRepository.delete(car);
		return new ApiResponse<>("Deleted", true);
	}

	@Override
	public ApiResponse<Car> getById(String id) {
		Car car = vehicleRepository.findById(id).orElseThrow(() -> new CarNotFoundException());
		car.setSoundLikeModel(datamuseService.get(car.getModel()));
		return new ApiResponse<>(true, car);
	}

	@Override
	public ApiResponse<List<Car>> getAll() {
		List<Car> cars = vehicleRepository.findAll();
		return new ApiResponse<>(true, cars);
	}

}
