package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersDeleteRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersCreateService;
import com.example.demo.service.UsersDeleteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Optional;
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
public class UsersDeleteControllerTest {

	@InjectMocks
	private UsersDeleteController usersDeleteController;

	@Mock
	private UsersDeleteService usersDeleteService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(usersDeleteController).build();
	}

	@Test
	public void whenDeleteUserSuccess() throws Exception {
		var usersDeleteRequest = UsersDeleteRequest
				.builder()
				.id("123")
				.build();

		when(usersDeleteService.execute(any())).thenReturn(new UsersCUDResponse());

		mockMvc
				.perform(delete("/users/123").characterEncoding("utf-8")
						.content(new ObjectMapper().writeValueAsString(usersDeleteRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
}
