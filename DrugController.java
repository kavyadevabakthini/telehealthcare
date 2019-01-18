package com.cybermate.drug.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cybermate.drug.model.Drug;
import com.cybermate.drug.service.IDrugService;

import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("api/v1")
public class DrugController {
	@Autowired
	private IDrugService drugService;

	@GetMapping("drug/{id}")
	public ResponseEntity<Drug> getDrugById(@PathVariable("id") Integer id) {
		Drug drug = drugService.getDrugById(id);
		return new ResponseEntity<Drug>(drug, HttpStatus.OK);
	}

	@GetMapping("drug")
	public ResponseEntity<List<Drug>> getAllDrugs() {
		List<Drug> list = drugService.getAllDrugs();
		return new ResponseEntity<List<Drug>>(list, HttpStatus.OK);

	}

	@PostMapping("drug/")
	public ResponseEntity<Void> addDrug(@RequestBody Drug drug, UriComponentsBuilder builder) {
		boolean flag = drugService.addDrug(drug);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/drug/{id}").buildAndExpand(drug.getDrug_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("drug/update")
	public ResponseEntity<Drug> updateDrug(@RequestBody Drug drug) {
		drugService.updateDrug(drug);
		return new ResponseEntity<Drug>(drug, HttpStatus.OK);
	}

}
