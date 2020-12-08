package com.example.demo.service;

import com.example.demo.model.request.UsersUploadRequest;
import com.example.demo.model.response.UsersUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersUploadService {

	public UsersUploadResponse execute(UsersUploadRequest input) {
		return UsersUploadResponse
				.builder()
				.message(input.getInput())
				.build();
	}
}
