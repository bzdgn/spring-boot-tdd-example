package com.levo.tdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levo.tdd.dto.PersonDTO;
import com.levo.tdd.service.PersonService;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	// crud
	@RequestMapping(value = "persons/{id}", method = RequestMethod.GET) 
	public PersonDTO getPersonById(@PathVariable Long id) {
		return personService.getPerson(id);
	}
	
	@RequestMapping(value = "persons", method = RequestMethod.GET) 
	public List<PersonDTO> getPersons() {
		return personService.getPersons();
	}
	
	@RequestMapping(value = "persons", method = RequestMethod.POST) 
	public PersonDTO createPerson(@RequestBody PersonDTO person) {
		return personService.createPerson(person);
	}
	
	@RequestMapping(value = "persons", method = RequestMethod.PUT) 
	public PersonDTO updatePerson(@RequestBody PersonDTO person) {
		return personService.updatePerson(person);
	}
	
	@RequestMapping(value = "persons", method = RequestMethod.DELETE) 
	public boolean deletePerson(@RequestBody Long id) {
		return personService.deletePerson(id);
	}
	
}
