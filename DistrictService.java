package com.service;

import java.util.List;

import com.model.Country;
import com.model.District;
import com.model.State;

public interface DistrictService {
	public List<District> getAll();

	public District getById(int id);

	public District getByName(String name);

	public List<District> getByState(State stateId);

	public String addDistrict(District district);

	public String updateDistrict(District district);
}
