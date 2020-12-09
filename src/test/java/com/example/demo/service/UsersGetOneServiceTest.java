package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersGetOneRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.model.response.UsersGetOneResponse;
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
public class UsersGetOneServiceTest {

	@InjectMocks
	private UsersGetOneService UsersGetOneService;

	@Mock
	private UsersRepository usersRepository;

	@Test
	public void whenGetOneUser() throws Exception {
		var usersGetOneRequest = UsersGetOneRequest
				.builder()
				.id("123")
				.build();

		var userEntity = UsersEntity.builder().id("123").startDate(new Date()).build();

		when(usersRepository.findById(any())).thenReturn(Optional.of(userEntity));

		var actual = UsersGetOneService.execute(usersGetOneRequest);
		Assertions.assertThat(actual)
				.isNotNull()
				.isExactlyInstanceOf(UsersGetOneResponse.class);
	}
}
