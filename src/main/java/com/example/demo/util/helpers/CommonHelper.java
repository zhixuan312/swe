package com.example.demo.util.helpers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommonHelper {
	public static Date dateFormatter(String source) {
		Date finalDate = new Date();
		List<String> datePattern = Arrays.asList("yy-MMM-dd", "yyyy-MM-dd");
		for (int i = 0; i < datePattern.size(); i++) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(datePattern.get(i));
				finalDate = formatter.parse(source);
				break;
			} catch (Exception e) { }
		}
		return finalDate;
	}
}
