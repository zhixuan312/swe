package com.example.demo.service;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.request.UsersUpdateRequest;
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
public class UsersUpdateService {

	@Autowired
	private final UsersRepository usersRepository;

	public UsersUpdateService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersCUDResponse execute(UsersUpdateRequest request, String id) {
		var existingUser = usersRepository.findById(id);
		if (existingUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10003.getErrorCode());
		}

		if (Objects.nonNull(request.getLogin())) {
			var loginUser = usersRepository.getUserByLogin(request.getLogin());
			if (Objects.nonNull(loginUser)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10004.getErrorCode());
			}
		}

		var usersEntity = UsersMapper.INSTANCE.fromUsersUpdateRequestToUsersEntity(request, existingUser.orElseThrow());
		usersEntity.setStartDate(CommonHelper.dateFormatter(request.getStartDate()));

		usersRepository.save(usersEntity);
		return UsersCUDResponse.builder().message("Successfully updated").build();
	}

}
