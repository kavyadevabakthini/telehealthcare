package com.service;

import java.util.List;

import com.model.District;
import com.model.Town;
import com.model.Zipcode;

public interface ZipcodeService {
	public List<Zipcode> getAll();

	public Zipcode getById(int id);

	public Zipcode getByName(String name);

	public Zipcode getByTown(Town town);

	public String addZipcode(Zipcode zipcode);

	public String updateZipcode(Zipcode zipcode);
}
