package com.example.demo.service;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.request.UsersGetOneRequest;
import com.example.demo.model.response.UsersGetOneResponse;
import com.example.demo.repository.UsersRepository;
import com.example.demo.util.enums.ErrorCodesEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UsersGetOneService {

	private final UsersRepository usersRepository;

	public UsersGetOneService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersGetOneResponse execute(UsersGetOneRequest request) {
		var userEntity = usersRepository.findById(request.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST , ErrorCodesEnum.ERR_10000.getErrorCode()));

		var usersGetOneResponse = UsersMapper.INSTANCE.fromUsersEntityToUsersGetOneResponse(userEntity);
		return usersGetOneResponse;
	}
}
