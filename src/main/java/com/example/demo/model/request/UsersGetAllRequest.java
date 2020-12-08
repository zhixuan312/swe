package com.example.demo.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class UsersGetAllRequest implements Serializable {
	private BigDecimal minSalary;
	private BigDecimal maxSalary;
	private int offset;
	private BigInteger limit;
}
