package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersGetAllRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.model.response.UsersGetAllResponse;
import com.example.demo.service.UsersCreateService;
import com.example.demo.service.UsersGetAllService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class UsersGetAllControllerTest {

	@InjectMocks
	private UsersGetAllController usersGetAllController;

	@Mock
	private UsersGetAllService usersGetAllService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(usersGetAllController).build();
	}

	@Test
	public void whenGetAllUserSuccess() throws Exception {
		var usersGetAllRequest = UsersGetAllRequest
				.builder()
				.offset(123)
				.minSalary(new BigDecimal(123))
				.maxSalary(new BigDecimal(123))
				.limit(new BigInteger(String.valueOf(123)))
				.build();

		when(usersGetAllService.execute(any())).thenReturn(new UsersGetAllResponse());

		mockMvc
				.perform(get("/users").characterEncoding("utf-8")
						.content(new ObjectMapper().writeValueAsString(usersGetAllRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
}
