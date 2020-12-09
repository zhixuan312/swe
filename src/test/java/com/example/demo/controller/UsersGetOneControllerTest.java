package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersGetOneRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.model.response.UsersGetAllResponse;
import com.example.demo.model.response.UsersGetOneResponse;
import com.example.demo.service.UsersCreateService;
import com.example.demo.service.UsersGetOneService;
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
public class UsersGetOneControllerTest {

	@InjectMocks
	private UsersGetOneController UsersGetOneController;

	@Mock
	private UsersGetOneService usersGetOneService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(UsersGetOneController).build();
	}

	@Test
	public void whenGetOneSuccess() throws Exception {
		var usersGetOneRequest = UsersGetOneRequest
				.builder()
				.id("123")
				.build();

		when(usersGetOneService.execute(any())).thenReturn(new UsersGetOneResponse());

		mockMvc
				.perform(get("/users/123").characterEncoding("utf-8")
						.content(new ObjectMapper().writeValueAsString(usersGetOneRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
}
