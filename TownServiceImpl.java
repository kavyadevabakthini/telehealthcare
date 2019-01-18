package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Credential;
import com.model.District;
import com.model.Town;
import com.repository.TownRepository;

@Service
public class TownServiceImpl implements TownService {
	
	@Autowired
	private TownRepository townRepository;
	

	public List<Town> getAll() {
		
		List<Town> list = new ArrayList<>();
		townRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public Town getById(int id) {
		
		return townRepository.findById(id).get();
	}

	public Town getByName(String name) {
	
		return townRepository.findByTownName(name);
	}

	public List<Town> getByDistrict(District districtId) {
		
		return townRepository.findByDistrict(districtId);
	}
	
	
	public String addTown(Town town) {
		Town persistedTown = getByName(town.getTownName());
		if (persistedTown != null) {
			return "Already Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy("Ramya");
			credential.setCreatedOn(new Date());
			credential.setActive(true);
			town.setCredential(credential);
			townRepository.save(town);
			return "Success";
		}
		
	}


	public String updateTown(Town town) {
		
		Town persistedTown = getById(town.getTownId());
		if (persistedTown == null) {
			return "Does not Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy(persistedTown.getCredential().getCreatedBy());
			credential.setCreatedOn(persistedTown.getCredential().getCreatedOn());
			credential.setUpdatedBy("Ramya");
			credential.setUpdatedOn(new Date());
			credential.setActive(persistedTown.getCredential().isActive());
			persistedTown.setCredential(credential);
			persistedTown.setTownName(town.getTownName());
			townRepository.save(persistedTown);
			return "Success";
		}
	}

}
