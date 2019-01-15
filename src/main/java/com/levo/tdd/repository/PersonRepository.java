package com.levo.tdd.repository;

import java.util.List;

import com.levo.tdd.dto.PersonDTO;

public interface PersonRepository {
	
	PersonDTO retrievePerson(Long id);
	List<PersonDTO> retrieveAll();
	boolean createPerson(PersonDTO person);
	PersonDTO updatePerson(PersonDTO person);
	boolean deletePerson(Long id);

}
