package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Credential;
import com.model.Town;
import com.model.Zipcode;
import com.repository.ZipcodeRepository;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {
    @Autowired
	private ZipcodeRepository zipcodeRepository;
    
	@Override
	public List<Zipcode> getAll() {
		List<Zipcode> list = new ArrayList<>();
		zipcodeRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Zipcode getById(int id) {
		return zipcodeRepository.findById(id).get();
	}

	@Override
	public Zipcode getByName(String name) {
		return zipcodeRepository.findByZipcodeName(name);
	}

	@Override
	public Zipcode getByTown(Town town) {
		return zipcodeRepository.findByTown(town);
	}

	@Override
	public String addZipcode(Zipcode zipcode) {
		Zipcode persistedZipcode = getByName(zipcode.getZipcodeName());
		if (persistedZipcode != null) {
			return "Already Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy("Ramya");
			credential.setCreatedOn(new Date());
			credential.setActive(true);
			zipcode.setCredential(credential);
			zipcodeRepository.save(zipcode);
			return "Success";
		}
		
	}

	@Override
	public String updateZipcode(Zipcode zipcode) {
		Zipcode persistedZipcode = getById(zipcode.getZipcodeId());
		if (persistedZipcode == null) {
			return "Does not Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy(persistedZipcode.getCredential().getCreatedBy());
			credential.setCreatedOn(persistedZipcode.getCredential().getCreatedOn());
			credential.setUpdatedBy("Ramya");
			credential.setUpdatedOn(new Date());
			credential.setActive(persistedZipcode.getCredential().isActive());
			persistedZipcode.setCredential(credential);
			persistedZipcode.setZipcodeName(zipcode.getZipcodeName());
			zipcodeRepository.save(persistedZipcode);
			return "Success";
	}

}
}
