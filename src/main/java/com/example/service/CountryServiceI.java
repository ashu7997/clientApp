package com.example.service;

import java.util.List;

import com.example.entity.Country;

public interface CountryServiceI {
	
	public List<Country> getAllCountries();
	
	public Country getCountryById(Integer Id);
	
	public Country getCountryByName(String countryName);
	
	public Country addCountry(Country country);
	
	public Country updateCountry(Country country,Integer id);
	
	public  void deleteCountry(Country country);

}
