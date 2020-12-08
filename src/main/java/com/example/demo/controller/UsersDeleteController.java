package com.example.demo.controller;


import com.example.demo.model.request.UsersDeleteRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersDeleteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersDeleteController {

	private final UsersDeleteService usersDeleteService;

	public UsersDeleteController(UsersDeleteService usersDeleteService) {
		this.usersDeleteService = usersDeleteService;
	}

	@ApiOperation("Delete User")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Successfully deleted"),
					@ApiResponse(code = 400, message = "Bad input")
			})
	@DeleteMapping("/{id}")
	public UsersCUDResponse deleteUser(@PathVariable String id) {
		var usersDeleteRequest = UsersDeleteRequest.builder().id(id).build();
		return usersDeleteService.execute(usersDeleteRequest);
	}
}
