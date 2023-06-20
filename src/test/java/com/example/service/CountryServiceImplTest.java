package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.repository.CountryRepo;
import java.util.ArrayList;
import java.util.List;
import com.example.entity.Country;

@SpringBootTest(classes= {CountryServiceImplTest.class})
class CountryServiceImplTest {
@Mock
private CountryRepo countryRepo;

@InjectMocks
private CountryServiceImpl countryServiceImpl;

@Test
public void getAllCountriesTest() {
	List<Country> mycountry=new ArrayList<>();
	mycountry.add(new Country(1,"India","Maharashtra"));
	mycountry.add(new Country(2,"USA","canada"));
	
	when(countryRepo.findAll()).thenReturn(mycountry);
	
	 List<Country> allCountries = countryServiceImpl.getAllCountries();
	 
	 int atualsize = allCountries.size();
	 
	 int expectedsize=2;
	 
	 assertEquals(expectedsize, atualsize);
	
}
@Test
public void getCountryByIdTest() {
	List<Country> mycountry=new ArrayList<>();
	mycountry.add(new Country(1,"India","Maharashtra"));
	mycountry.add(new Country(2,"USA","canada"));
	
	int countryid=1;
	
	when(countryRepo.findAll()).thenReturn(mycountry);
	
	Integer actualresult = countryServiceImpl.getCountryById(countryid).getCountryId();
	
	assertEquals(countryid, actualresult);
	
	
}
@Test
public void getCountryByNameTest() {
	
	List<Country> mycountry=new ArrayList<>();
	mycountry.add(new Country(1,"India","Maharashtra"));
	mycountry.add(new Country(2,"USA","canada"));
	
	String countryname="India";
	
	when(countryRepo.findAll()).thenReturn(mycountry);
	
	String actualname = countryServiceImpl.getCountryByName(countryname).getCountryName();
	
	assertEquals(countryname, actualname);
	
}
@Test
public void addCountryTest() {
	Country country=new Country(1,"India","MAharashtra");
	
	when(countryRepo.save(country)).thenReturn(country);
	
	Country news = countryServiceImpl.addCountry(country);
	
	assertEquals(country, news);
	
}
@Test
public void updateCountryTest() {
	Country country=new Country(1,"India","Maharashtra");
	
	when(countryRepo.save(country)).thenReturn(country);
	
	int countryid=1;
	
	Country updateCountry = countryServiceImpl.updateCountry(country, countryid);
	
	assertEquals(country, updateCountry);
	
}
@Test
public void deleteCountryTest() {
	
	Country country=new Country(1,"India","Maharashtra");
	countryServiceImpl.deleteCountry(country);
	
	verify(countryRepo, times(1)).delete(country);
	
	
}

}


