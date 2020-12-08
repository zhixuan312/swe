package com.example.demo.util.helpers;

import com.example.demo.model.entity.UsersEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CsvHelper {

	public static String TYPE = "text/csv";
	static String[] HEADERs = {"id", "login", "name", "salary", "startDate"};

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<UsersEntity> csvToUsers(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<UsersEntity> users = new ArrayList<>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				System.out.println(csvRecord);
				UsersEntity user = UsersEntity
						.builder()
						.id(csvRecord.get(0))
						.login(csvRecord.get(1))
						.name(csvRecord.get(2))
						.salary(new BigDecimal(csvRecord.get(3)))
						.startDate(CommonHelper.dateFormatter(csvRecord.get(4)))
						.build();

				users.add(user);
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
