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
import org.springframework.web.util.UriComponentsBuilder;

import com.cybermate.drug.model.Disease;

import com.cybermate.drug.service.IDiseaseService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("api/v1")
public class DiseaseController {
	

		@Autowired
		private IDiseaseService diseaseService;

		@GetMapping("disease/{id}")
		public ResponseEntity<Disease> getDiseaseById(@PathVariable("id") Integer id) {
			Disease disease = diseaseService.getDiseaseById(id);
			return new ResponseEntity<Disease>(disease, HttpStatus.OK);
		}

		@GetMapping("disease")
		public ResponseEntity<List<Disease>> getAllDiseases() {
			List<Disease> list = diseaseService.getAllDiseases();
			return new ResponseEntity<List<Disease>>(list, HttpStatus.OK);

		}

		@PostMapping("disease/")
		public ResponseEntity<Void> addDisease(@RequestBody Disease disease, UriComponentsBuilder builder) {
			boolean flag = diseaseService.addDisease(disease);
			if (flag == false) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/disease/{id}").buildAndExpand(disease.getDisease_id()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		@PutMapping("disease/update")
		public ResponseEntity<Disease> updateDisease(@RequestBody Disease disease) {
			diseaseService.updateDisease(disease);
			return new ResponseEntity<Disease>(disease, HttpStatus.OK);
		}

	}
