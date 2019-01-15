package com.levo.tdd.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.levo.tdd.dto.PersonDTO;
import com.levo.tdd.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersonServiceImpl.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceTest {
	
	@MockBean
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	private Object output;
	private static final PersonDTO PERSON_1 = new PersonDTO(12L, "Levent", "Divilioglu");
	private static final PersonDTO PERSON_2 = new PersonDTO(18L, "Jane", "Doe");
	private static final PersonDTO UPDATED_PERSON = new PersonDTO(18L, "Levent", "Doe");
	private static final List<PersonDTO> PERSONS = new ArrayList<>(Arrays.asList(PERSON_1, PERSON_2));
	
	@Test
	public void test01_shouldCreatePerson() {
		given(personRepository.createPerson(PERSON_1)).willReturn(PERSON_1);
		whenCreatingPerson(PERSON_1);
		thenObjectReceived(PERSON_1);
	}
	
	@Test
	public void test02_shouldGetExistingPerson() {
		given(personRepository.retrievePerson(12L)).willReturn(PERSON_1);
		whenGettingPerson(12L);
		thenObjectReceived(PERSON_1);
	}
	
	@Test
	public void test03_shouldGetNullWhenPersonDoesNotExist() {
		given(personRepository.retrievePerson(13L)).willReturn(null);
		whenGettingPerson(13L);
		thenObjectReceived(null);
	}
	
	@Test
	public void test04_shouldGetExistingPersons() {
		given(personRepository.retrieveAll()).willReturn(PERSONS);
		whenGettingPersons();
		thenObjectReceived(PERSONS);
	}
	
	@Test
	public void test05_shouldUpdateExistingPerson() {
		given(personRepository.updatePerson(UPDATED_PERSON)).willReturn(UPDATED_PERSON);
		whenUpdatingPerson(UPDATED_PERSON);
		thenObjectReceived(UPDATED_PERSON);
	}
	
	@Test
	public void test06_shouldDeleteExistingPerson() {
		given(personRepository.deletePerson(12L)).willReturn(true);
		whenDeletingPerson(12L);
		thenObjectReceived(true);
	}
	
	// helper methods
	private void whenCreatingPerson(PersonDTO person) {
		output = personService.createPerson(person);
	}
	
	private void whenGettingPerson(Long id) {
		output = personService.getPerson(id);
	}
	
	private void whenGettingPersons() {
		output = personService.getPersons();
	}
	
	private void whenUpdatingPerson(PersonDTO person) {
		output = personService.updatePerson(person);
	}
	
	private void whenDeletingPerson(Long id) {
		output = personService.deletePerson(id);
	}
	
	private void thenObjectReceived(Object object) {
		assertEquals(object, output);
	}
	
}
