package com.example.demo.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UsersEntity {
	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "salary", nullable = false)
	private String salary;

	@Column(name = "start_date", nullable = false)
	private Date startDate;
}
