package com.example.demo.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode()
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersGetOneResponse implements Serializable {

	private String id;
	private String name;
	private String login;
	private BigDecimal salary;
	private String startDate;
}
