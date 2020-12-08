package com.example.demo.controller;

import com.example.demo.model.request.UsersUploadRequest;
import com.example.demo.model.response.UsersUploadResponse;
import com.example.demo.service.UsersUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/upload")
@Slf4j
public class UsersUploadController {

	private final UsersUploadService usersUploadService;

	public UsersUploadController(UsersUploadService usersUploadService) {
		this.usersUploadService = usersUploadService;
	}

	@ApiOperation("Upload Users CSV")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success"),
					@ApiResponse(code = 400, message = "Invalid input"),
					@ApiResponse(code = 404, message = "Not found"),
					@ApiResponse(code = 500, message = "Internal system error")
			})
	@PostMapping("/")
	public UsersUploadResponse uploadUser(@RequestBody @Valid UsersUploadRequest request) {
		return usersUploadService.execute(request);
	}
}
