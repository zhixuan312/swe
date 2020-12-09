package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersDeleteRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersDeleteServiceTest {

	@InjectMocks
	private UsersDeleteService usersDeleteService;

	@Mock
	private UsersRepository usersRepository;

	@Test
	public void whenDeleteUser() throws Exception {
		var usersDeleteRequest = UsersDeleteRequest
				.builder()
				.id("123")
				.build();

		var userEntity = UsersEntity.builder().id("123").build();

		when(usersRepository.findById(any())).thenReturn(Optional.of(userEntity));

		var actual = usersDeleteService.execute(usersDeleteRequest);


		Assertions.assertThat(actual)
				.isNotNull()
				.isExactlyInstanceOf(UsersCUDResponse.class);
	}
}
