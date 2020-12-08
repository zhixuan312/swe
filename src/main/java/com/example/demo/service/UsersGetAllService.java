package com.example.demo.service;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.request.UsersGetAllRequest;
import com.example.demo.model.response.UsersGetAllResponse;
import com.example.demo.repository.UsersRepository;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersGetAllService {

	private final UsersRepository usersRepository;

	public UsersGetAllService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersGetAllResponse execute(UsersGetAllRequest request) {
		var allUsers = usersRepository.getAllUsers(request.getMinSalary(), request.getMaxSalary(), request.getOffset(),
				request.getLimit());
		var listResponse = allUsers.stream()
				.map(UsersMapper.INSTANCE::fromUsersEntityToUsersGetOneResponse)
				.collect(Collectors.toList());
		return UsersGetAllResponse.builder().users(listResponse).build();
	}
}
