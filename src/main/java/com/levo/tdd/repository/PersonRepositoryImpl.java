package com.levo.tdd.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.levo.tdd.dto.PersonDTO;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Override
	public PersonDTO retrievePerson(Long id) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	@Override
	public List<PersonDTO> retrieveAll() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	@Override
	public boolean deletePerson(Long id) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

}
