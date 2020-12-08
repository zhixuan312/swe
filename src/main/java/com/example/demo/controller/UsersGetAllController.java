package com.example.demo.controller;

import com.example.demo.model.request.UsersGetAllRequest;
import com.example.demo.model.response.UsersGetAllResponse;
import com.example.demo.service.UsersGetAllService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersGetAllController {

	private final UsersGetAllService usersGetAllService;

	public UsersGetAllController(UsersGetAllService usersGetAllService) {
		this.usersGetAllService = usersGetAllService;
	}

	@ApiOperation("Get All User")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success but no data updated"),
					@ApiResponse(code = 400, message = "Bad input")
			})
	@GetMapping("")
	public UsersGetAllResponse getAllUser(@RequestParam(required = false, defaultValue = "0") String minSalary,
			@RequestParam(required = false, defaultValue = "4000") String maxSalary,
			@RequestParam(required = false, defaultValue = "0") String offset,
			@RequestParam(required = false, defaultValue = "18446744073709551610") String limit) {
		var request = UsersGetAllRequest
				.builder()
				.limit(new BigInteger(limit))
				.maxSalary(new BigDecimal(maxSalary))
				.minSalary(new BigDecimal(minSalary))
				.offset(Integer.valueOf(offset))
				.build();
		return usersGetAllService.execute(request);
	}
}
