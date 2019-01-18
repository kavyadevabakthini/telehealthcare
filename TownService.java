package com.service;

import java.util.List;

import com.model.District;
import com.model.Town;

public interface TownService {
	public List<Town> getAll();

	public Town getById(int id);

	public Town getByName(String name);

	public List<Town> getByDistrict(District districtId);

	public String addTown(Town town);

	public String updateTown(Town town);
}
