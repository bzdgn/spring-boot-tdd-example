package com.levo.tdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.levo.tdd.controller.error.NotFoundException;
import com.levo.tdd.dto.PersonDTO;
import com.levo.tdd.service.PersonService;

@RestController
@RequestMapping("/tdd-example")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	// crud
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK)
	public PersonDTO getPersonById(@PathVariable("id") Long id) {
		PersonDTO result = personService.getPerson(id);
		
		return result;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK)
	public List<PersonDTO> getPersons() {
		List<PersonDTO> result =  personService.getPersons();
		
		return result;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.POST) 
	@ResponseStatus(HttpStatus.CREATED)
	public PersonDTO createPerson(@RequestBody PersonDTO person) {
		PersonDTO result = personService.createPerson(person);
		
		return result;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.PUT) 
	@ResponseStatus(HttpStatus.OK)
	public PersonDTO updatePerson(@RequestBody PersonDTO person) {
		PersonDTO result = personService.updatePerson(person);
		
		if(result == null) {
			throw new NotFoundException();
		} else {
			return result;
		}
	}
	
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE) 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable("id") Long id) {
		boolean result = personService.deletePerson(id);
		
		if(result == false) {
			throw new NotFoundException();
		}
	}
	
}
