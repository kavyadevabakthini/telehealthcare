package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Country;
import com.model.Credential;
import com.model.District;
import com.model.State;
import com.repository.DistrictRepository;

@Service
public class DistrictServiceImpl implements DistrictService {
    
	@Autowired
	private DistrictRepository districtRepository;

	public List<District> getAll() {
		List<District> list = new ArrayList<>();
		districtRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public District getById(int id) {
		return districtRepository.findById(id).get();
	}

	public District getByName(String name) {
		
		return districtRepository.findByDistrictName(name);
	}

	public List<District> getByState(State stateId) {
		
		return districtRepository.findByState(stateId);
	}

	public String addDistrict(District district) {
		
		District persistedDistrict = getByName(district.getDistrictName());
		if (persistedDistrict != null) {
			return "Already Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy("Ramya");
			credential.setCreatedOn(new Date());
			credential.setActive(true);
			district.setCredential(credential);
			districtRepository.save(district);
			return "Success";
		}
	}
	
	public String updateDistrict(District district) {
		System.out.println(district.toString());
		District persistedDistrict = getById(district.getDistrictId());
		if (persistedDistrict == null) {
			return "Does not Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy(persistedDistrict.getCredential().getCreatedBy());
			credential.setCreatedOn(persistedDistrict.getCredential().getCreatedOn());
			credential.setUpdatedBy("Ramya");
			credential.setUpdatedOn(new Date());
			credential.setActive(persistedDistrict.getCredential().isActive());
			persistedDistrict.setCredential(credential);
			persistedDistrict.setDistrictName(district.getDistrictName());
			persistedDistrict.setState(district.getState());
			persistedDistrict.setCountry(district.getCountry());
			districtRepository.save(persistedDistrict);
			return "Success";
		}
	}

}
