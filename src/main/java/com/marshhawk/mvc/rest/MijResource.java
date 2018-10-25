package com.marshhawk.mvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marshhawk.mvc.model.Mij;
import com.marshhawk.mvc.model.MijRepository;

@RestController
@RequestMapping("/bibrecords/mij/v1")
public class MijResource {
	
	@Autowired
	private MijRepository repository;
	
	@RequestMapping(path="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Mij>> getMijRecords(){
		List<Mij> marcs = repository.findAll();
		return new ResponseEntity<>(marcs,HttpStatus.OK);
	}
}
