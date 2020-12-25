package com.teqto.trackme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teqto.trackme.model.User;

/**
 * @author jayant
 *
 */
public interface UsersRepository extends JpaRepository<User, Integer>{
	User findByEmail(String emailId);
	User findByPhone(String phone);
	Optional<User> findByDeviceid(String deviceid);
	Optional<User> findByUnverifiedDeviceid(String deviceid);
	User validatePwd(@Param("phone") String emailId,@Param("password") String password);
	@Transactional
	@Modifying
	int forgetPwd(@Param("phone") String phone,@Param("password") String password);
	@Transactional
	@Modifying
	int activateUser(@Param("email") String emailId,@Param("active") Boolean active);
}
