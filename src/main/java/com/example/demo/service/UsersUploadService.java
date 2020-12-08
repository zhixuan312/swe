package com.example.demo.service;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.repository.UsersRepository;
import com.example.demo.util.enums.ErrorCodesEnum;
import com.example.demo.util.helpers.CsvHelper;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class UsersUploadService {

	@Autowired
	private final UsersRepository usersRepository;

	public UsersUploadService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public UsersCUDResponse execute(MultipartFile file) {

		if (CsvHelper.hasCSVFormat(file)) {
			try {
				save(file);
				return UsersCUDResponse.builder().message("Data created or uploaded").build();
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10005.getErrorCode());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodesEnum.ERR_10006.getErrorCode());
		}
	}

	public void save(MultipartFile file) {
		try {
			List<UsersEntity> users = CsvHelper.csvToUsers(file.getInputStream());
			usersRepository.saveAll(users);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

}
