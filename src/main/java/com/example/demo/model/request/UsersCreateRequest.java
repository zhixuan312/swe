package com.example.demo.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
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
public class UsersCreateRequest implements Serializable {

	@NotBlank
	@Null
	private String id;
	@NotBlank
	@Null
	private String name;
	@NotBlank
	@Null
	private String login;
	@NotBlank
	@Null
	private BigDecimal salary;
	@NotBlank
	@Null
	private String startDate;
}
