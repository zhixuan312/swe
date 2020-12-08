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
	ERR_10004("Employee login not unique"),
	ERR_10005("Bad input - parsing error, duplicate row, invalid salary etc"),
	ERR_10006("Bad input - no file");
	private final String errorCode;
}
