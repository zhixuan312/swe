package com.example.demo.controller;

import com.example.demo.model.request.UsersGetOneRequest;
import com.example.demo.model.response.UsersGetOneResponse;
import com.example.demo.service.UsersGetOneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersGetOneController {

	private final UsersGetOneService usersGetOneService;

	public UsersGetOneController(UsersGetOneService usersGetOneService) {
		this.usersGetOneService = usersGetOneService;
	}

	@ApiOperation("Get User By Id")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success"),
					@ApiResponse(code = 400, message = "Invalid input"),
					@ApiResponse(code = 404, message = "Not found"),
					@ApiResponse(code = 500, message = "Internal system error")
			})
	@GetMapping("/{id}")
	public UsersGetOneResponse uploadUserData(@PathVariable @Valid String id) {
		var usersGetOneRequest = UsersGetOneRequest.builder().id(id).build();
		return usersGetOneService.execute(usersGetOneRequest);
	}
}
