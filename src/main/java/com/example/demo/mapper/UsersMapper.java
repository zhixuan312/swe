package com.example.demo.mapper;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.entity.UsersEntity.UsersEntityBuilder;
import com.example.demo.model.request.UsersCreateRequest;
import com.example.demo.model.request.UsersUpdateRequest;
import com.example.demo.model.response.UsersGetOneResponse;
import com.example.demo.model.response.UsersGetOneResponse.UsersGetOneResponseBuilder;
import java.text.SimpleDateFormat;
import java.util.Objects;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsersMapper {

	public static final UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

	@Mapping(target = "id", source = "usersEntity.id")
	@Mapping(target = "name", source = "usersEntity.name")
	@Mapping(target = "login", source = "usersEntity.login")
	@Mapping(target = "salary", source = "usersEntity.salary")
	@BeanMapping(qualifiedByName = "responseFormatting")
	public abstract UsersGetOneResponse fromUsersEntityToUsersGetOneResponse(UsersEntity usersEntity);

	@Mapping(target = "id", source = "usersCreateRequest.id")
	@Mapping(target = "name", source = "usersCreateRequest.name")
	@Mapping(target = "login", source = "usersCreateRequest.login")
	@Mapping(target = "salary", source = "usersCreateRequest.salary")
	@Mapping(target = "startDate", ignore = true)
	public abstract UsersEntity fromUsersCreateRequestToUsersEntity(UsersCreateRequest usersCreateRequest);

	@Mapping(target = "id", source = "usersEntity.id")
	@Mapping(target = "login", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "salary", ignore = true)
	@Mapping(target = "startDate", ignore = true)
	@BeanMapping(qualifiedByName = "updateMapping")
	public abstract UsersEntity fromUsersUpdateRequestToUsersEntity(UsersUpdateRequest usersUpdateRequest,
			UsersEntity usersEntity);

	@Named("responseFormatting")
	@AfterMapping
	void responseFormatting(@MappingTarget UsersGetOneResponseBuilder target, UsersEntity source) {
		if (Objects.nonNull(source)) {
			var formatter = new SimpleDateFormat("yyyy-MM-dd");
			target.startDate(formatter.format(source.getStartDate()));
		}
	}

	@Named("updateMapping")
	@AfterMapping
	void updateMapping(@MappingTarget UsersEntityBuilder target, UsersUpdateRequest usersUpdateRequest,
			UsersEntity source) {
		if (Objects.nonNull(source)) {
			if (Objects.nonNull(usersUpdateRequest.getLogin())) {
				target.login(usersUpdateRequest.getLogin());
			} else {
				target.login(source.getLogin());
			}

			if (Objects.nonNull(usersUpdateRequest.getName())) {
				target.name(usersUpdateRequest.getName());
			} else {
				target.name(source.getName());
			}

			if (Objects.nonNull(usersUpdateRequest.getSalary())) {
				target.salary(usersUpdateRequest.getSalary());
			} else {
				target.salary(source.getSalary());
			}
		}
	}

}
