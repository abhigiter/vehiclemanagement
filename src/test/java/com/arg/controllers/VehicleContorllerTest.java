package com.arg.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arg.dtos.ApiResponse;
import com.arg.entities.Car;
import com.arg.repository.VehicleRepository;
import com.arg.services.VehicleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author AbhijeetG
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VehicleContorllerTest {
	
	protected MockHttpSession mockSession;
	
	private static final String BASE_PATH = "/secure/";
	
	@Autowired
	VehicleController controller;
	
	@Autowired
	VehicleService<Car> service;
	
	@Autowired
	VehicleRepository repository;
	
	MockMvc mockMvc;
	
	private Car car;
	private Car carToDelete;
	private Car carToUpdate;
	
	
	@Before
	public void onSetUp() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		mockSession = new MockHttpSession();
		request.setSession(mockSession);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		prepareData();
		carToDelete = repository.saveAndFlush(carToDelete);
		carToUpdate = repository.saveAndFlush(carToUpdate);
	}
	
	 @After
	 public void cleanup() {
	   repository.deleteAllByMake("HyundaiTest");
	} 
	
	public void prepareData() {
		car = new Car();
		car.setColour("Silver");
		car.setMake("HyundaiTest");
		car.setModel("2020");
		car.setYear(2019);
		
		carToDelete = new Car();
		carToDelete.setColour("Red");
		carToDelete.setMake("HyundaiTest");
		carToDelete.setModel("Creta");
		carToDelete.setYear(2020);
		
		carToUpdate = new Car();
		carToUpdate.setColour("White");
		carToUpdate.setMake("HyundaiTest");
		carToUpdate.setModel("Venue");
		carToUpdate.setYear(2021);
	}
	
	@Test
    public void saveCar() throws Exception { 
		
		//given
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_PATH + "/car")
				.content(new ObjectMapper().writeValueAsString(car)).contentType(MediaType.APPLICATION_JSON)
				.session(mockSession);
		
		
		//when
		ResultActions response = mockMvc.perform(requestBuilder);
		MvcResult result = response.andReturn();
		
		String content = result.getResponse().getContentAsString();
		ApiResponse<Car> res = new ObjectMapper().readValue(content, new TypeReference<ApiResponse<Car>>() {});
		car = (Car)res.getResult();

		// then
		response.andExpect(status().isOk());
	}
	
	
	@Test
	public void updateCar() throws Exception {
		
		//given
		carToUpdate.setColour("Black");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(BASE_PATH + "/car/" + carToUpdate.getId())
				.content(new ObjectMapper().writeValueAsString(carToUpdate)).contentType(MediaType.APPLICATION_JSON)
				.session(mockSession);
		
		//when
		ResultActions response = mockMvc.perform(requestBuilder);
		MvcResult result = response.andReturn();
		
		String content = result.getResponse().getContentAsString();
		ApiResponse<Car> res = new ObjectMapper().readValue(content, new TypeReference<ApiResponse<Car>>() {});
		carToUpdate = (Car)res.getResult();

		// then
		response.andExpect(status().isOk());
		assertEquals("Black", carToUpdate.getColour());
	}
	
	
	@Test
    public void getCar() throws Exception { 
		
		//given
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_PATH + "/car/" + carToUpdate.getId()).contentType(MediaType.APPLICATION_JSON)
				.session(mockSession);
		
		
		//when
		ResultActions response = mockMvc.perform(requestBuilder);

		// then
		response.andExpect(status().isOk());
	}
	
	 @Test
	 public void deleteCar() throws Exception {
		 

			//given
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_PATH + "/car/" + carToDelete.getId()).contentType(MediaType.APPLICATION_JSON)
					.session(mockSession);
			
			
			//when
			ResultActions response = mockMvc.perform(requestBuilder);

			// then
			response.andExpect(status().isOk());
	 }
	 
	 @Test
	 public void deleteCarIfNotExists() throws Exception {
		 
		 
		 //given
		 
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_PATH + "/car/1234").contentType(MediaType.APPLICATION_JSON)
				 .session(mockSession);
		 
		 //when
		 ResultActions response = mockMvc.perform(requestBuilder);
		 
		 // then
		 response.andExpect(status().isNotFound());
	 }
	
	
	
}
