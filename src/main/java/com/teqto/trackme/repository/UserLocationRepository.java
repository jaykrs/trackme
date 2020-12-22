package com.teqto.trackme.repository;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.teqto.trackme.model.Userlocation;

/**
 * @author jayant
 *
 */
public interface UserLocationRepository extends JpaRepository<Userlocation, Integer>{
	Optional<List<Userlocation>> findByUserid(@Param("userid") Integer userid) throws URISyntaxException,NoSuchElementException;
	Optional<List<Userlocation>> findByDateAndUserid(@Param("createdon") LocalDateTime createdon, @Param("userid") Integer userid) throws URISyntaxException,NoSuchElementException;
}
