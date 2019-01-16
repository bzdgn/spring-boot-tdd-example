package com.levo.tdd.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.levo.tdd.dto.PersonDTO;
import com.levo.tdd.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersonController.class, TestContext.class })
@EnableWebMvc
public class PersonControllerTest extends BaseControllerTest {
	
	private static final PersonDTO PERSON_1 = new PersonDTO(12L, "Levent", "Divilioglu");
	private static final PersonDTO PERSON_2 = new PersonDTO(18L, "Jane", "Doe");
	private static final PersonDTO UPDATED_PERSON = new PersonDTO(18L, "Levent", "Doe");
	private static final List<PersonDTO> PERSONS = new ArrayList<>(Arrays.asList(PERSON_1, PERSON_2));
	
	@MockBean
	private PersonService personService;

	@Before
	public void setup() {
		setupMock();
	}

	@Test
	public void test_01_shouldCreatePersonOnPost() {
		given(personService.createPerson(PERSON_1)).willReturn(PERSON_1);
		whenCreatingPerson(PERSON_1);
		thenHttpResponseStatusCodeIs(HttpStatus.CREATED.value());
		thenResponseBodyHasContentWithObjectAndSuccessfulResponse(PERSON_1);
	}

	@Test
	public void test_02_shouldGetCreatedPerson() {
		given(personService.getPerson(PERSON_1.getId())).willReturn(PERSON_1);
		whenGettingPerson(PERSON_1.getId());
		thenHttpResponseStatusCodeIs(HttpStatus.OK.value());
		thenResponseBodyHasContentWithObjectAndSuccessfulResponse(PERSON_1);
	}

	@Test
	public void test_03_shouldGetCreatedPersons() {
		given(personService.getPersons()).willReturn(PERSONS);
		whenGettingPersons();
		thenHttpResponseStatusCodeIs(HttpStatus.OK.value());
		thenResponseBodyHasContentWithListAndSuccessfulResponse(PERSONS);
	}

	@Test
	public void test_04_shouldModifyCreatedPerson() {
		given(personService.updatePerson(UPDATED_PERSON)).willReturn(UPDATED_PERSON);
		whenModifyingPersons(UPDATED_PERSON);
		thenHttpResponseStatusCodeIs(HttpStatus.OK.value());
		thenResponseBodyHasContentWithObjectAndSuccessfulResponse(UPDATED_PERSON);
	}

	@Test
	public void test_05_shouldModifyNotCreatedPersonFail() {
		given(personService.updatePerson(UPDATED_PERSON)).willReturn(null);
		whenModifyingPersons(UPDATED_PERSON);
		thenHttpResponseStatusCodeIs(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void test_06_shouldDeleteCreatedPerson() {
		given(personService.deletePerson(PERSON_1.getId())).willReturn(true);
		whenDeletingPerson(PERSON_1.getId());
		thenHttpResponseStatusCodeIs(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void test_07_shouldDeleteNotCreatedPersonFail() {
		given(personService.deletePerson(13L)).willReturn(false);
		whenDeletingPerson(13L);
		thenHttpResponseStatusCodeIs(HttpStatus.NOT_FOUND.value());
	}

	private void whenCreatingPerson(PersonDTO p) {
		String serializedPerson = getSerializedObject(p);

		try {
			requestResult = mockMvc.perform(post("/tdd-example/persons").content(serializedPerson)
					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();

			PERSONS.add(p);
		} catch (Exception e) {
			fail("Test failed: Cannot apply POST to the controller: " + e.getMessage());
		}
	}

	private void whenGettingPerson(Long id) {
		try {
			requestResult = mockMvc.perform(get("/tdd-example/persons/" + id).accept(MediaType.APPLICATION_JSON))
					.andReturn();
		} catch (Exception e) {
			fail("Test failed: Cannot apply GET to the controller: " + e.getMessage());
		}
	}

	private void whenGettingPersons() {
		try {
			requestResult = mockMvc.perform(get("/tdd-example/persons").accept(MediaType.APPLICATION_JSON)).andReturn();
		} catch (Exception e) {
			fail("Test failed: Cannot apply GET to the controller: " + e.getMessage());
		}
	}

	private void whenModifyingPersons(PersonDTO modifiedPerson) {
		String serializedModifiedPerson = getSerializedObject(modifiedPerson);

		try {
			requestResult = mockMvc.perform(put("/tdd-example/persons").content(serializedModifiedPerson)
					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();

			PERSONS.remove(0);
			PERSONS.add(modifiedPerson);
		} catch (Exception e) {
			fail("Test failed: Cannot apply PUT to the controller: " + e.getMessage());
		}
	}

	private void whenDeletingPerson(Long id) {
		try {
			requestResult = mockMvc.perform(delete("/tdd-example/persons/" + id)).andReturn();

			PERSONS.remove(0);
		} catch (Exception e) {
			e.printStackTrace();
//			fail("Test failed: Cannot apply DELETE to the controller: " + e.getMessage());
		}
	}

	private void thenResponseBodyHasContentWithObjectAndSuccessfulResponse(PersonDTO person) {

		try {
			String body = requestResult.getResponse().getContentAsString();

			PersonDTO p = (PersonDTO) getObjectFromString(body, PersonDTO.class);

			assertEquals(person, p);
		} catch (UnsupportedEncodingException e) {
			fail("Test failed: cannot check response body");
		}

	}

	@SuppressWarnings("unchecked")
	private void thenResponseBodyHasContentWithListAndSuccessfulResponse(List<PersonDTO> expectedPersonList) {

		try {
			String body = requestResult.getResponse().getContentAsString();

			List<PersonDTO> actualPersonList = (List<PersonDTO>) getListOfObjectsFromString(body,
					new TypeReference<List<PersonDTO>>() {
					});

			assertEquals(expectedPersonList, actualPersonList);
		} catch (UnsupportedEncodingException e) {
			fail("Test failed: cannot check response body");
		}

	}

}
