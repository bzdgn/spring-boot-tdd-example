package com.levo.tdd.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.levo.tdd.dto.PersonDTO;

@Primary
@Repository
public class PersonRepositoryArrayListImpl implements PersonRepository {
	
	private ArrayList<PersonDTO> personStorage = new ArrayList<>();

	@Override
	public PersonDTO retrievePerson(Long id) {
		return findById(id);
	}

	@Override
	public List<PersonDTO> retrieveAll() {
		return personStorage;
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) {
		if(findById(person.getId()) == null) {
			personStorage.add(person);
			
			return person;
		} else {
			return null;
		}
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) {
		PersonDTO p = findById(person.getId());
		
		if(p == null) {
			return null;
		} else {
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			
			return p;
		}
	}

	@Override
	public boolean deletePerson(Long id) {
		PersonDTO p = findById(id);
		
		if(p == null) {
			return false;
		} else {
			personStorage.remove(p);
			
			return true;
		}
	}
	
	private PersonDTO findById(Long id) {
		for(PersonDTO p : personStorage) {
			if(p.getId().equals(id)) {
				return p;
			}
		}
		
		return null;
	}
	
}
