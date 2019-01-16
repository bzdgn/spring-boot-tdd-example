package com.levo.tdd.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levo.tdd.dto.PersonDTO;

public class BaseControllerTest {

	protected MockMvc mockMvc;
	protected MvcResult requestResult;

	@Autowired
	protected ObjectMapper mapper;

	@Autowired
	protected WebApplicationContext wac;

	protected void setupMock() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	protected void thenHttpResponseStatusCodeIs(int expectedStatusCode) {
		int actual = requestResult.getResponse().getStatus();

		assertEquals(expectedStatusCode, actual);
	}

	protected String getSerializedObject(Object o) {
		String serialized;
		try {
			serialized = mapper.writeValueAsString(o);

			return serialized;
		} catch (JsonProcessingException e) {
			fail("Test failed: Cannot serialize Object");
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	protected Object getObjectFromString(String str, Class clazz) {
		try {
			Object o = mapper.readValue(str, clazz.newInstance().getClass());

			return o;
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			fail("Test failed: Cannot Deserialize: " + e.getMessage());
		}

		return null;
	}

	protected List<? extends Object> getListOfObjectsFromString(String str, TypeReference<List<PersonDTO>> typeReference) {
		try {
			List<Object> list = mapper.readValue(str, typeReference);

			return list;
		} catch (IOException e) {
			fail("Test failed: Cannot Deserialize List: " + e.getMessage());
		}

		return null;
	}

}
