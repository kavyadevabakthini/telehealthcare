package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Country;
import com.model.State;

public interface StateService {
	public List<State> getAll();

	public State getById(int id);

	public State getByName(String stateName);
	
	public List<State> getByCountryId(Country id);

	public String addState(State state);

	public String updateState(State state);
}
