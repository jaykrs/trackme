package com.teqto.trackme.repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.teqto.trackme.model.Usergroup;

/**
 * @author jayant
 *
 */
public interface UserGroupRepository extends JpaRepository<Usergroup, Integer>{
	Optional<List<Usergroup>> findByOwnerid(@Param("ownerid") Integer ownerid) throws URISyntaxException,NoSuchElementException;
	Optional<List<Usergroup>> findByUserid(@Param("userid") Integer userid) throws URISyntaxException,NoSuchElementException;
	Optional<List<Usergroup>> findByGroupOnwerAndUser(@Param("groupid") Integer groupid,@Param("userid") Integer userid) throws URISyntaxException,NoSuchElementException;
	Optional<List<Usergroup>> findByUserAndGroup(@Param("groupid") Integer groupid,@Param("userid") Integer userid) throws URISyntaxException,NoSuchElementException;
}
