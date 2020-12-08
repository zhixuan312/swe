package com.example.demo.service;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersCreateServiceTest {

	@InjectMocks
	private UsersCreateService usersCreateService;

	@Test
	public void whenCreateUser() throws Exception {
		var usersCreateRequest = UsersCreateRequest
				.builder()
				.id("123")
				.login("123")
				.name("123")
				.salary(new BigDecimal(123))
				.startDate("2020-12-01")
				.build();

		var actual = usersCreateService.execute(usersCreateRequest);
		Assertions.assertThat(actual)
				.isNotNull()
				.isExactlyInstanceOf(UsersCUDResponse.class);
	}
}
