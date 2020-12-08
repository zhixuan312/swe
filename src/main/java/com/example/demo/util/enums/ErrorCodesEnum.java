package com.example.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodesEnum {
	ERR_10000("no such employee"),
	ERR_10001("Employee ID already exists"),
	ERR_10002("Employee login not unique"),
	ERR_10003("No such employee"),
	ERR_10004("Employee login not unique");
	private final String errorCode;
}
