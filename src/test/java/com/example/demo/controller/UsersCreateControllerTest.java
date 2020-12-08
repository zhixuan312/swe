package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersCreateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UsersCreateControllerTest {

	@InjectMocks
	private UsersCreateController usersCreateController;

	@Mock
	private UsersCreateService usersCreateService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(usersCreateController).build();
	}

	@Test
	public void whenCreateUserSuccess() throws Exception {
		var usersCreateRequest = UsersCreateRequest
				.builder()
				.id("123")
				.login("123")
				.name("123")
				.salary(new BigDecimal(123))
				.startDate("2020-12-01")
				.build();

		when(usersCreateService.execute(any())).thenReturn(new UsersCUDResponse());

		mockMvc
				.perform(post("/users").characterEncoding("utf-8")
						.content(new ObjectMapper().writeValueAsString(usersCreateRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
}
