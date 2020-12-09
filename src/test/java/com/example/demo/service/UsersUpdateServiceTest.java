package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersUpdateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UsersUpdateServiceTest {

	@InjectMocks
	private UsersUpdateService usersUpdateService;

	@Mock
	private UsersRepository usersRepository;

	@Test
	public void whenCreateUser() throws Exception {
		var usersUpdateRequest = UsersUpdateRequest
				.builder()
				.login("123")
				.name("123")
				.salary(new BigDecimal(123))
				.startDate("2020-12-01")
				.build();

		var userEntity = UsersEntity.builder().id("123").startDate(new Date()).build();

		when(usersRepository.findById(any())).thenReturn(Optional.of(userEntity));

		var actual = usersUpdateService.execute(usersUpdateRequest, "123");
		Assertions.assertThat(actual)
				.isNotNull()
				.isExactlyInstanceOf(UsersCUDResponse.class);
	}
}
