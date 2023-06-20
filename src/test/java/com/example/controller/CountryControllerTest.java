package com.example.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.service.CountryServiceImpl;
import com.example.entity.Country;
import java.util.List;
import java.util.ArrayList;




@SpringBootTest(classes= {CountryControllerTest.class})
class CountryControllerTest {
	
	@Mock
	private CountryServiceImpl countryServiceImpl;
	
	@InjectMocks
	private CountryController countryController;

	
	@Test
  public void addCountryTest() {
		Country country=new Country(1,"India","Maharashtra");
		
		when(countryServiceImpl.addCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> newcountry = countryController.addCountry(country);
		
		Country country2 = newcountry.getBody();
		
		HttpStatus actualstatusCode = newcountry.getStatusCode();
		
		System.out.println(actualstatusCode);
		String actualcountryName = country2.getCountryName();
		
		String expected="India";
		
		
		assertEquals(expected, actualcountryName);
		
		assertEquals(HttpStatus.CREATED, actualstatusCode);
	  
		
		HttpHeaders headers = newcountry.getHeaders();
		System.out.println(headers);
  }

	@Test
public void getAllCountriesTest() {
	List<Country> mycountry=new ArrayList<>();
	mycountry.add(  new Country( 1, "India","Maharashtra"));
	mycountry.add(new Country(2,"canada","france"));
	
	when(countryServiceImpl.getAllCountries()).thenReturn(mycountry);
	
	ResponseEntity<List<Country>> allCountries = countryController.getAllCountries();
	
	HttpStatus statusCode = allCountries.getStatusCode();
	
	assertEquals(HttpStatus.FOUND, statusCode);
	
}
	@Test
public void getCountryByIdTest() {
		Country country=new Country(1,"India","goa");
		
		int countryId=1;
		
		when(countryServiceImpl.getCountryById(countryId)).thenReturn(country);
		
		ResponseEntity<Country> countryById = countryController.getCountryById(countryId);
		
		HttpStatus statusCode = countryById.getStatusCode();
		
		assertEquals(HttpStatus.FOUND, statusCode);
	}
	
	@Test
	public void getCountryByNameTest() {
		Country country=new Country(1,"India","goa");
		
		String countryName="India";
		
		when(countryServiceImpl.getCountryByName(countryName)).thenReturn(country);
		
		ResponseEntity<Country> countryByName = countryController.getCountryByName(countryName);
		
		/*
		 * HttpStatus statusCode = countryByName.getStatusCode();
		 * assertEquals(HttpStatus.FOUND, statusCode);
		 */
		
		Country body = countryByName.getBody();
		String countryName2 = body.getCountryName();
		
		assertEquals(countryName, countryName2);
		
		
		
	}
	@Test
	public void updateCountryTest() {
		Country country=new Country(1,"India","goa");
		
		
		
		int countryId=1;
		
		when(countryServiceImpl.updateCountry(country, countryId)).thenReturn(country);
		
		ResponseEntity<Country> updatedCountry = countryController.updateCountry(country, countryId);
		
		Country body = updatedCountry.getBody();
		
		int countryId1 = body.getCountryId();
		
		assertEquals(countryId, countryId1);
	}
	@Test	
	public void deleetCountryTest() {
		
		Country country=new Country(1,"India","goa");
		
		countryServiceImpl.deleteCountry(country);
		
		ResponseEntity<Country> deleteCountry = countryController.deleteCountry(country);
		
		HttpStatus statusCode = deleteCountry.getStatusCode();
		
		assertEquals(HttpStatus.OK, statusCode);
		
	}
	


}
