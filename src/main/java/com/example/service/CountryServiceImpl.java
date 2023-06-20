package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Country;
import com.example.repository.CountryRepo;

@Service
public class CountryServiceImpl implements CountryServiceI{

	@Autowired
	private CountryRepo countryRepo;
	
	@Override
	public List<Country> getAllCountries() {
		
		List<Country> contries = countryRepo.findAll();
		
		return contries;
	}

	@Override
	public Country getCountryById(Integer Id) {
		List<Country> all = countryRepo.findAll();
		Country country=null;
		
		for(Country con:all) {
			if(con.getCountryId()==Id)
				country=con;
		}
		return country;
	}

	@Override
	public Country getCountryByName(String countryName) {
		List<Country> countries = countryRepo.findAll();
		
		Country country=null;
		
		for(Country con:countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName))
			country=con;
		
		}
		return country;
	}

	@Override
	public Country addCountry(Country country) {
		Country save = countryRepo.save(country);
		
		return save;
	}

	@Override
	public Country updateCountry(Country country, Integer id) {
		country.setCountryId(id);
		Country save = countryRepo.save(country);
		return save;
	}

	@Override
	public void deleteCountry(Country country) {
      countryRepo.delete(country);		
	}

}
