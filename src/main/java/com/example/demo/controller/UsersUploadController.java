package com.example.demo.controller;

import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/upload")
@Slf4j
public class UsersUploadController {

	@Autowired
	private final UsersUploadService usersUploadService;

	public UsersUploadController(UsersUploadService usersUploadService) {
		this.usersUploadService = usersUploadService;
	}

	@ApiOperation("Upload Users CSV")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success"),
					@ApiResponse(code = 400, message = "Invalid input"),
					@ApiResponse(code = 401, message = "Not found")
			})
	@PostMapping("")
	public UsersCUDResponse uploadUser(@RequestParam("file") MultipartFile file) {

		return usersUploadService.execute(file);
	}
}
