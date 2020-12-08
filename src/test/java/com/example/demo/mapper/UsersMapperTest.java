package com.example.demo.mapper;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.response.UsersGetOneResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersMapperTest {

	private UsersMapperImpl usersMapper = new UsersMapperImpl();

	private UsersEntity usersEntity;
	private UsersGetOneResponse expected;

	@Before
	public void setUp() throws Exception {
		var formatter = new SimpleDateFormat("yyyy-MM-dd");
		var mockDate = formatter.parse("2020-12-12");

		usersEntity = UsersEntity
				.builder()
				.id("123")
				.name("123")
				.login("123")
				.salary(new BigDecimal(123))
				.startDate(mockDate)
				.build();

		expected = UsersGetOneResponse
				.builder()
				.login("123")
				.id("123")
				.name("123")
				.salary(new BigDecimal(123))
				.startDate("2020-12-12")
				.build();
	}

	@Test
	public void whenFromUsersEntityToUsersGetOneResponse() {
		var actual = usersMapper
				.fromUsersEntityToUsersGetOneResponse(usersEntity);
		Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
	}
}
