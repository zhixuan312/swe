package com.example.demo.controller;

import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersCreateService;
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
@RequestMapping("/users")
@Slf4j
public class UsersCreateController {

	private final UsersCreateService usersCreateService;

	public UsersCreateController(UsersCreateService usersCreateService) {
		this.usersCreateService = usersCreateService;
	}

	@ApiOperation("Create User")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "New employee record created"),
					@ApiResponse(code = 400, message = "Bad input")
			})
	@PostMapping("")
	public UsersCUDResponse createUser(@RequestBody @Valid UsersCreateRequest request) {
		return usersCreateService.execute(request);
	}
}
