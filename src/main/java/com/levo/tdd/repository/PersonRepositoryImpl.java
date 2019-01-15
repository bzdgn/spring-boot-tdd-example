package com.levo.tdd.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.levo.tdd.dto.PersonDTO;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Override
	public PersonDTO retrievePerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonDTO> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) {
		// TODO Auto-generated method stub
		return person;
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePerson(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
