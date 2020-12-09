package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersUpdateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersCreateService;
import com.example.demo.service.UsersUpdateService;
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
public class UsersUpdateControllerTest {

	@InjectMocks
	private UsersUpdateController usersUpdateController;

	@Mock
	private UsersUpdateService usersUpdateService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(usersUpdateController).build();
	}

	@Test
	public void whenUpdateUserSuccess() throws Exception {
		var usersUpdateRequest = UsersUpdateRequest
				.builder()
				.login("123")
				.name("123")
				.salary(new BigDecimal(123))
				.startDate("2020-12-01")
				.build();

		when(usersUpdateService.execute(any(), any())).thenReturn(new UsersCUDResponse());

		mockMvc
				.perform(put("/users/123").characterEncoding("utf-8")
						.content(new ObjectMapper().writeValueAsString(usersUpdateRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
}
