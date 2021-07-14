/**
 * 
 */
package com.arg.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



/**
 * @author AbhijeetG
 *
 */
@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@Column(name = "id", length = 36)
	private String id; 
	
	@Column(name = "make")
	@NotNull(message = "make may not be null")
	private String make;
	
	@Column(name = "model")
	@NotNull(message = "model may not be null")
	private String model;
	
	@Column(name = "colour")
	private String colour;
	
	@Column(name = "year")
	@NotNull(message = "year may not be null")
	private Integer year;
	
	@Transient
	private String soundLikeModel;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSoundLikeModel() {
		return soundLikeModel;
	}

	public void setSoundLikeModel(String soundLikeModel) {
		this.soundLikeModel = soundLikeModel;
	}

	@PrePersist
	public void prePersist() {
		this.setId(UUID.randomUUID().toString());
	}
	
}
