package com.example.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodesEnum {
	ERR_10000("invalid user id");
	private final String errorCode;
}
