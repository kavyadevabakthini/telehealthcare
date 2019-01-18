package com.cybermate.drug.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybermate.drug.model.Drug;
import com.cybermate.drug.model.PhysicalExamTxn;
import com.cybermate.drug.model.PhysicalExamTxnDtl;

import com.cybermate.drug.service.IExamService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("api/v1")
public class ExamController {
@Autowired
IExamService examService;

@GetMapping("all")
public ResponseEntity<List<PhysicalExamTxnDtl>> getAllDtls() {
	List<PhysicalExamTxnDtl> list = examService.getAllDtls();
	return new ResponseEntity<List<PhysicalExamTxnDtl>>(list, HttpStatus.OK);

}

@GetMapping("edit/{mrno}/{eid}/{vid}")
public ResponseEntity<PhysicalExamTxn> getExamTxnByRecord(@PathVariable("mrno") String mrno,@PathVariable("eid") String eid,@PathVariable("vid") String vid) {
	PhysicalExamTxn list = examService.getByMrnoEidVid(mrno, eid, vid).get(0);
	return new ResponseEntity<PhysicalExamTxn>(list, HttpStatus.OK);
}
@RequestMapping(value = "/edit-txn", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
public ResponseEntity<Void> addTxnDtls(@RequestBody PhysicalExamTxn examTxn, UriComponentsBuilder builder) {
	System.out.println("posting......");
	
	PhysicalExamTxn txn =new PhysicalExamTxn();
	PhysicalExamTxnDtl txndtl;
	txn.setMedicalRecordNo(examTxn.getMedicalRecordNo());
	txn.setEpisodeId(examTxn.getEpisodeId());
	txn.setVisitId(examTxn.getVisitId());
	txn.setNotes(examTxn.getNotes());

	List<PhysicalExamTxnDtl> list = new ArrayList<>();

	for (int i = 0; i < examTxn.getExamTxnDtls().size(); i++) {
		
		txndtl =new PhysicalExamTxnDtl();
		txndtl.setExamTxn(txn);
		txndtl.setExamType(examTxn.getExamTxnDtls().get(i).getExamType());
		txndtl.setExamSpecific(examTxn.getExamTxnDtls().get(i).getExamSpecific());
		txndtl.setStatus(examTxn.getExamTxnDtls().get(i).getStatus());
		txndtl.setDescription(examTxn.getExamTxnDtls().get(i).getDescription());
		txndtl.setExamProgress(examTxn.getExamTxnDtls().get(i).getExamProgress());
		txndtl.setExamDate(examTxn.getExamTxnDtls().get(i).getExamDate());
		txndtl.setCreatedBy(examTxn.getExamTxnDtls().get(i).getCreatedBy());
		txndtl.setCreatedOn(examTxn.getExamTxnDtls().get(i).getCreatedOn());
		txndtl.setUpdatedBy(examTxn.getExamTxnDtls().get(i).getUpdatedBy());
		txndtl.setUpdatedOn(examTxn.getExamTxnDtls().get(i).getUpdatedOn());
		list.add(txndtl);
		//System.out.println(examTxn.getExamTxnDtls().get(i).getExamType());
	}
	txn.setExamTxnDtls(list);

	examService.addTxn(txn);

	return new ResponseEntity<Void>(HttpStatus.CREATED);
}

@RequestMapping(value = "/exam/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
public ResponseEntity<Void>updateTxnDtls(@RequestBody PhysicalExamTxn examTxn, UriComponentsBuilder builder) {
	System.out.println("updating......");
	
	PhysicalExamTxn txn =new PhysicalExamTxn();
	PhysicalExamTxnDtl txndtl;
	txn.setId(examTxn.getId());
	txn.setMedicalRecordNo(examTxn.getMedicalRecordNo());
	txn.setEpisodeId(examTxn.getEpisodeId());
	txn.setVisitId(examTxn.getVisitId());
	txn.setNotes(examTxn.getNotes());

	List<PhysicalExamTxnDtl> list = new ArrayList<>();

	for (int i = 0; i < examTxn.getExamTxnDtls().size(); i++) {
		
		txndtl =new PhysicalExamTxnDtl();
		txndtl.setExamTxn(txn);
		txndtl.setExamId(examTxn.getExamTxnDtls().get(i).getExamId());
		txndtl.setExamType(examTxn.getExamTxnDtls().get(i).getExamType());
		txndtl.setExamSpecific(examTxn.getExamTxnDtls().get(i).getExamSpecific());
		txndtl.setStatus(examTxn.getExamTxnDtls().get(i).getStatus());
		txndtl.setDescription(examTxn.getExamTxnDtls().get(i).getDescription());
		txndtl.setExamProgress(examTxn.getExamTxnDtls().get(i).getExamProgress());
		txndtl.setExamDate(examTxn.getExamTxnDtls().get(i).getExamDate());
		txndtl.setCreatedBy(examTxn.getExamTxnDtls().get(i).getCreatedBy());
		txndtl.setCreatedOn(examTxn.getExamTxnDtls().get(i).getCreatedOn());
		txndtl.setUpdatedBy(examTxn.getExamTxnDtls().get(i).getUpdatedBy());
		txndtl.setUpdatedOn(examTxn.getExamTxnDtls().get(i).getUpdatedOn());
		list.add(txndtl);
		//System.out.println(examTxn.getExamTxnDtls().get(i).getExamType());
	}
	txn.setExamTxnDtls(list);

	examService.addTxn(txn);

	return new ResponseEntity<Void>(HttpStatus.CREATED);
}


@DeleteMapping("delete/{id}")

public ResponseEntity<Void> deleteTxn(@PathVariable("id") int id) {
	System.out.println("delete called");
	System.out.println(id);
	examService.deleteTxn(id);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}



}
