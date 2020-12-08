package com.example.demo.service;


import com.example.demo.model.request.UsersDeleteRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import com.example.demo.util.enums.ErrorCodesEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UsersDeleteService {

	@Autowired
	private final UsersRepository usersRepository;

	public UsersDeleteService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersCUDResponse execute(UsersDeleteRequest request) {
		var existingUser = usersRepository.findById(request.getId());
		if (existingUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10003.getErrorCode());
		}

		usersRepository.delete(existingUser.orElseThrow());
		return UsersCUDResponse.builder().message("Successfully deleted").build();
	}
}
