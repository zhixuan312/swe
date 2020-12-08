package com.example.demo.repository;

import com.example.demo.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

	@Query(value = "SELECT * from Users e where e.login =:login", nativeQuery = true)
	UsersEntity getUserByLogin(@Param("login") String login);

}
