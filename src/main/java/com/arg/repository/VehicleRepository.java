/**
 * 
 */
package com.arg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.arg.entities.Car;

/**
 * @author AbhijeetG
 *
 */
public interface VehicleRepository extends JpaRepository<Car, String> {

	/**
	 * @param string
	 */
	
	@Transactional
	@Modifying
	void deleteAllByMake(String make);

}
