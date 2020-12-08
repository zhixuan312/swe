package com.example.demo.service;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import com.example.demo.util.enums.ErrorCodesEnum;
import com.example.demo.util.helpers.CommonHelper;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UsersCreateService {

	@Autowired
	private final UsersRepository usersRepository;

	public UsersCreateService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersCUDResponse execute(UsersCreateRequest request) {
		var existingUser = usersRepository.findById(request.getId());
		if (existingUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10001.getErrorCode());
		}

		var loginUser = usersRepository.getUserByLogin(request.getLogin());
		if (Objects.nonNull(loginUser)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10002.getErrorCode());
		}

		var usersEntity = UsersMapper.INSTANCE.fromUsersCreateRequestToUsersEntity(request);
		usersEntity.setStartDate(CommonHelper.dateFormatter(request.getStartDate()));

		usersRepository.save(usersEntity);
		return UsersCUDResponse.builder().message("Successfully created").build();
	}

}
