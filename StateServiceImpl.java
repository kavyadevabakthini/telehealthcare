package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Country;
import com.model.Credential;
import com.model.State;
import com.repository.StateRepository;
@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateRepository stateRepository;

	public List<State> getAll() {

		List<State> list = new ArrayList<>();
		stateRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public State getById(int stateId) {
		
		return stateRepository.findById(stateId).get();
	}

	public State getByName(String stateName) {
		
		return stateRepository.findByStateName(stateName);
	}
	
	public List<State> getByCountryId(Country id) {
		 return  stateRepository.findByCountry(id);
	}

	public String addState(State state) {
		System.out.println(state.toString());
		State persistedState = getByName(state.getStateName());
		if (persistedState != null) {
			return "Already Exist";
		} else {
			
			Credential credential = new Credential();
			credential.setCreatedBy("Ramya");
			credential.setCreatedOn(new Date());
			credential.setActive(true);
			state.setCredential(credential);
			stateRepository.save(state);
			return "Success";
		}

	}

	public String updateState(State state) {
	   State persistedState = getById(state.getStateId());
		System.out.println(persistedState.toString());
		if (persistedState == null) {
			return "Does not Exist";
		} else {
			Credential credential = new Credential();
			credential.setCreatedBy(persistedState.getCredential().getCreatedBy());
			credential.setCreatedOn(persistedState.getCredential().getCreatedOn());
			credential.setUpdatedBy("Ramya");
			credential.setUpdatedOn(new Date());
			credential.setActive(persistedState.getCredential().isActive());
			persistedState.setCredential(credential);
			persistedState.setStateName(state.getStateName());
			persistedState.setCountry(state.getCountry());
			stateRepository.save(persistedState);
			return "Success";
		}
	}



}
