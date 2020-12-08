package com.example.demo.controller;

import com.example.demo.model.request.UsersUpdateRequest;
import com.example.demo.model.response.UsersCUDResponse;
import com.example.demo.service.UsersUpdateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersUpdateController {

	private final UsersUpdateService usersUpdateService;

	public UsersUpdateController(UsersUpdateService usersUpdateService) {
		this.usersUpdateService = usersUpdateService;
	}

	@ApiOperation("Update User")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Successfully updated"),
					@ApiResponse(code = 400, message = "Bad input")
			})
	@PutMapping("/{id}")
	public UsersCUDResponse updateUser(@RequestBody @Valid UsersUpdateRequest request, @PathVariable String id) {
		return usersUpdateService.execute(request, id);
	}
}
