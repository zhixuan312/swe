package com.example.demo.repository;

import com.example.demo.model.entity.UsersEntity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

	@Query(value = "SELECT * from Users e where e.login =:login", nativeQuery = true)
	UsersEntity getUserByLogin(@Param("login") String login);

	@Query(value = "SELECT * FROM Users e WHERE e.salary >= :minSalary AND e.salary <= :maxSalary LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<UsersEntity> getAllUsers(@Param("minSalary") BigDecimal minSalary, @Param("maxSalary") BigDecimal maxSalary,
			@Param("offset") int offset, @Param("limit") BigInteger limit);

}
