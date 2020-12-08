package com.example.demo.mapper;

import com.example.demo.model.entity.UsersEntity;
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
	public abstract UsersGetOneResponse from(UsersEntity usersEntity);

	@Named("responseFormatting")
	@AfterMapping
	void responseFormatting(@MappingTarget UsersGetOneResponseBuilder target, UsersEntity source){
		if (Objects.nonNull(source)) {
			var formatter = new SimpleDateFormat("yyyy-MM-dd");
			target.startDate(formatter.format(source.getStartDate()));
		}
	}
}
