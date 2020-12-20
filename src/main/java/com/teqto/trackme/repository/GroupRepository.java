package com.teqto.trackme.repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.teqto.trackme.model.Group;

/**
 * @author jayant
 *
 */
public interface GroupRepository extends JpaRepository<Group, Integer>{
	Optional<List<Group>> findByName(String name) throws URISyntaxException,NoSuchElementException;
	Optional<List<Group>> findByOwnerid(@Param("ownerid") Integer ownerid) throws URISyntaxException,NoSuchElementException;
	Optional<List<Group>> findByPhone(String phone) throws URISyntaxException,NoSuchElementException;
}
