package com.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Country;
import com.model.Credential;
import com.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAll() {

		List<Country> list = new ArrayList<>();
		countryRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public Country getById(int countryId) {
		
		return countryRepository.findById(countryId).get();
	}

	public Country getByName(String countryName) {
		
		return countryRepository.getByCountryName(countryName);
	}

	public String addCountry(Country country) {
		Country persistedCountry = getByName(country.getCountryName());
		if (persistedCountry != null) {
			return "Already Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy("Ramya");
			credential.setCreatedOn(new Date());
			credential.setActive(true);
			country.setCredential(credential);
			countryRepository.save(country);
			return "Success";
		}

	}

	public String updateCountry(Country country) {
		System.out.println(country.toString());
		Country persistedCountry = getById(country.getCountryId());
		System.out.println(persistedCountry.toString());
		if (persistedCountry == null) {
			return "Does not Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy(persistedCountry.getCredential().getCreatedBy());
			credential.setCreatedOn(persistedCountry.getCredential().getCreatedOn());
			credential.setUpdatedBy("Ramya");
			credential.setUpdatedOn(new Date());
			credential.setActive(persistedCountry.getCredential().isActive());
			persistedCountry.setCredential(credential);
			persistedCountry.setCountryName(country.getCountryName());
			countryRepository.save(persistedCountry);
			return "Success";
		}
	}

}
