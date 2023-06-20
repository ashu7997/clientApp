package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Country;
import com.example.service.CountryServiceI;
@Repository
public interface CountryRepo extends JpaRepository<Country, Integer>{
	
	

}
