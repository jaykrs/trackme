package com.teqto.trackme.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teqto.trackme.constants.ServiceConstants;
import com.teqto.trackme.model.Group;
import com.teqto.trackme.model.User;
import com.teqto.trackme.model.Usergroup;
import com.teqto.trackme.model.Userlocation;
import com.teqto.trackme.repository.GroupRepository;
import com.teqto.trackme.repository.UserGroupRepository;
import com.teqto.trackme.repository.UserLocationRepository;
import com.teqto.trackme.repository.UsersRepository;



/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/userlocation")
public class UserLocationController {

	private final Logger log = LoggerFactory.getLogger(UserLocationController.class);
	@Autowired
	private UserLocationRepository userLocationRepository;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getUserLocationById(@PathVariable Integer id) throws URISyntaxException,NoSuchElementException{
		Optional<Userlocation> ul = userLocationRepository.findById(id);
		log.info("found Userlocation with id" + id);
		return ul.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getLocationByUserId/{userid}")
	ResponseEntity<?> getUserLocationByUserId(@PathVariable Integer userid) throws URISyntaxException,NoSuchElementException{
		Optional<List<Userlocation>> ul = userLocationRepository.findByUserid(userid);
		log.info("found Userlocation with id" + userid);
		return ul.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	ResponseEntity<?> createUserLocation(@Valid @RequestBody Userlocation ul,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException {
		Userlocation result = null;
		log.info("Request to create Userlocation : {}", ul);
		if (ul.getUserid() > 0 && null != ul.getDeviceid() && null != ul.getLatitude() && null != ul.getLongitude()) {
			ul.setCreatedon(LocalDateTime.now());
			result = userLocationRepository.saveAndFlush(ul);
			return ResponseEntity.created(new URI("/api/userlocation/create/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(ul.getUserid());
	}
	
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
