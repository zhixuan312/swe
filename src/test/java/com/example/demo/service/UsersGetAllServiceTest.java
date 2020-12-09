package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.request.UsersGetAllRequest;
import com.example.demo.model.response.UsersGetAllResponse;
import com.example.demo.repository.UsersRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersGetAllServiceTest {

	@InjectMocks
	private UsersGetAllService usersGetAllService;

	@Mock
	private UsersRepository usersRepository;

	@Test
	public void whenGetAllUsers() throws Exception {
		var usersGetAllRequest = UsersGetAllRequest
				.builder()
				.limit(new BigInteger(String.valueOf(123)))
				.maxSalary(new BigDecimal(123))
				.minSalary(new BigDecimal(123))
				.offset(123)
				.build();

		var userEntity = UsersEntity.builder().id("123").name("123").login("123").salary(new BigDecimal(123))
				.startDate(new Date()).build();
		var users = Arrays.asList(userEntity);

		when(usersRepository.getAllUsers(any(), any(), any(Integer.class), any())).thenReturn(users);

		var actual = usersGetAllService.execute(usersGetAllRequest);
		Assertions.assertThat(actual)
				.isNotNull()
				.isExactlyInstanceOf(UsersGetAllResponse.class);
	}
}
