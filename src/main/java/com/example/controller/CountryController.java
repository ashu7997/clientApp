package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Country;
import com.example.exception.UserNotFoundException;
import com.example.service.CountryServiceI;

@RestController
//@RequestMapping("/countries")
public class CountryController {
	
	
	@Autowired
    private	CountryServiceI countryServiceI;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Country>> getAllCountries(){
		
		List<Country> countries = countryServiceI.getAllCountries();
		
		
		return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		
	}
	@PostMapping("/savecountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		
		if(country==null) {
			throw new UserNotFoundException("user not found");
		}else {
			Country addCountry = countryServiceI.addCountry(country);
			return new ResponseEntity<Country>(addCountry,HttpStatus.CREATED);
		}
		
		
		
	}
       @GetMapping("/getCountryById/{id}")
	public ResponseEntity<Country> getCountryById(Integer id){
	
	if(id==null) {
		throw new UserNotFoundException("user not found");
	}else {
		Country countryById = this.countryServiceI.getCountryById(id);
		return new ResponseEntity<Country>(countryById,HttpStatus.FOUND);
	}
		
	}
    @GetMapping("/getCountryByName")
   public ResponseEntity<Country> getCountryByName(@RequestParam String  countryName){
	if(countryName==null) {
  throw new UserNotFoundException("user not found");
	}else {
		Country countryByName = this.countryServiceI.getCountryByName(countryName);
		return new ResponseEntity<Country>(countryByName,HttpStatus.FOUND);
	}
}     
    @PutMapping("/updateCountry")
     public ResponseEntity<Country> updateCountry(Country country,Integer Id){
		Country updateCountry = this.countryServiceI.updateCountry(country, Id);
    	
    	return new ResponseEntity<Country>(updateCountry,HttpStatus.OK) ;
    	 
     }
   @DeleteMapping("/deleteCountry")
   public ResponseEntity<Country> deleteCountry(Country country){
	   this.countryServiceI.deleteCountry(country);
	 return new ResponseEntity<Country>(HttpStatus.OK);
	 
   }
}
