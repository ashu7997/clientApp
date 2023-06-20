package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.service.CountryServiceImpl;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.build.AndroidDescriptor;

import com.example.entity.Country;
import java.util.List;
import java.util.ArrayList;
@SpringBootTest(classes=CountryControllerTest2.class)
@AutoConfigureMockMvc
@ContextConfiguration
@ComponentScan(basePackages = "com.example")
class CountryControllerTest2 {

	
   @Mock
	private CountryServiceImpl countryServiceImpl;
   
   @InjectMocks
   private CountryController countryController;
   
   @Autowired
  private  MockMvc mockMvc;
   
   @BeforeEach
   public void setUp() {
	   mockMvc= MockMvcBuilders.standaloneSetup(countryController).build();
	   
   }
   @Test
   public void addCountryTest() throws Exception {
	   
	   Country country=new Country(1,"India","Maharashtra");
	   
	   when(countryServiceImpl.addCountry(country)).thenReturn(country);
	   
	   ObjectMapper mapper=new ObjectMapper();
	   
	   String countryAsString = mapper.writeValueAsString(country);
	   
	   mockMvc.perform(post("/savecountry")
			   .content(countryAsString)
			   .contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated()).andDo(print());
   }

   @Test
   public void getAllCountriesTest() throws Exception {
	   
	   List<Country> country=new ArrayList<>();
	   
	   country.add(new Country(1,"India","MAharashtra"));
	   country.add(new Country(2,"Canada","barlie"));
	   
	   when(countryServiceImpl.getAllCountries()).thenReturn(country);
	   
	   mockMvc.perform(get("/getall")).andExpect(status().isFound()).andDo(print());
	   
	  
 }
   @Test
   public void getCountryByIdTest() throws Exception {
	   
	   Country country=new Country(1,"India","Maharashtra");
	   
	   int countryId=1;
	   
	   when(countryServiceImpl.getCountryById(countryId)).thenReturn(country);
	   
	   mockMvc.perform(get("/getCountryById/{id}"))
	   .andExpect(status().isFound())
	   .andExpect(MockMvcResultMatchers.jsonPath(".countryId"). value(1))
	   .andExpect(MockMvcResultMatchers.jsonPath(".countryName"). value("India"))
	   .andExpect(MockMvcResultMatchers.jsonPath(".stateName"). value("Maharashtra"))
	   .andDo(print());
	   
			   
	   
	   
	   
   }

}
