/**
 * 
 */
package com.arg.servicesImpl;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.arg.utils.Constants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author AbhijeetG
 *
 */
@Service
public class DatamuseService {
	/*
	 * sincere note :  I could have create DTO to hold the data from datamuse as  
	 * that is the standard practice but lacking of time and using JSONArray
	 */
	
	public String get(String model){
		String similarToModel = "";
	    try {
			HttpResponse<JsonNode> jsonResponse  = Unirest.get(Constants.DATAMUSEURL + "/words?sl=" + model).asJson();
			if( jsonResponse.getStatus()== HttpStatus.SC_OK && jsonResponse.getBody().getArray().length() > 0) {
				JSONArray array = jsonResponse.getBody().getArray();
				int length  = array.length() > 5 ? 5 : array.length();
				
				/* this is just an fast adjustment 
				 * Java 8 / functional approach is the best practice
				 * */
				
				for(int i = 0 ; i< length ; i++ ) {
					JSONObject object = (JSONObject)array.get(i);
					similarToModel += object.get("word") + ", ";
				}
				
			}
			return similarToModel.replaceAll(", $", "");
		} catch (UnirestException e) {
			e.printStackTrace();
			return similarToModel;
		}
	}

	
}
