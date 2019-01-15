package com.levo.tdd.service;

import java.util.List;

import com.levo.tdd.dto.PersonDTO;

public interface PersonService {
	
	PersonDTO createPerson(PersonDTO person);
	PersonDTO getPerson(Long id);
	List<PersonDTO> getPersons();
	PersonDTO updatePerson(PersonDTO person);
	boolean deletePerson(Long id);

}
