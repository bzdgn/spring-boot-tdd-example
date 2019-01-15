package com.levo.tdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.levo.tdd.dto.PersonDTO;
import com.levo.tdd.repository.PersonRepository;

public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public PersonDTO createPerson(PersonDTO person) {
		boolean isSuccessful = personRepository.createPerson(person);
		
		if(isSuccessful) {
			return person;
		} else {
			return null;
		}
	}

	@Override
	public PersonDTO getPerson(Long id) {
		return personRepository.retrievePerson(id);
	}

	@Override
	public List<PersonDTO> getPersons() {
		return personRepository.retrieveAll();
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) {
		return personRepository.updatePerson(person);
	}

	@Override
	public boolean deletePerson(Long id) {
		return personRepository.deletePerson(id);
	}

}
