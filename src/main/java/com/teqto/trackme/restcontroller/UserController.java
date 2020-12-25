package com.teqto.trackme.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teqto.trackme.constants.ServiceConstants;
import com.teqto.trackme.email.EmailInterface;
import com.teqto.trackme.model.User;
import com.teqto.trackme.repository.UsersRepository;



/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UsersRepository usersRepository;
//	@Autowired
//	private TransectionRepository transectionRepository;
	@Autowired
	private EmailInterface emailUtils;

	@Autowired
	private Environment environment;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getUsersById(@PathVariable Integer id) {
		Optional<User> users = usersRepository.findById(id);
		log.info("found user with id" + id);
		users.get().setPassword("***");
		return users.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/getbydeviceid", method = RequestMethod.POST)
	ResponseEntity<?> getUsersByDeviceId(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) {
		Optional<User> users = usersRepository.findByDeviceid(json.get(ServiceConstants.DEVICETOKEN));
		log.info("found user with phone" + users.get().getPhone());
		users.get().setPassword("***");
		return users.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	ResponseEntity<?> createUser(@Valid @RequestBody User user,
			HttpServletRequest request) throws URISyntaxException {
		User result = null;
		log.info("Request to create user: {}", user);
		if (null != user.getPhone() && null == usersRepository.findByPhone(user.getPhone())
				&& null != user.getDeviceid()) {
			user.setOtp(UUID.randomUUID().toString().substring(0, 4));
			user.setActive(Boolean.FALSE);
			user.setCreatedon(LocalDate.now());
			user.setContact(user.getPhone());
			result = usersRepository.saveAndFlush(user);
			emailUtils.sendOtpMessage(user.getPhone(), user.getOtp());
			result.setPassword(Strings.EMPTY);
			result.setOtp(Strings.EMPTY);
			return ResponseEntity.created(new URI("/api/user/create/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(user.getPhone() + ServiceConstants.PHONEEXISTS);
	}
	
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/approveotp", method = RequestMethod.POST)
	ResponseEntity<?> approveOtpUser(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		User result = null;
		log.info("Request to validate user: {}", json.get("userid"));
		User user = usersRepository.findByUnverifiedDeviceid(null != json.get(ServiceConstants.DEVICETOKEN)?json.get(ServiceConstants.DEVICETOKEN):Strings.EMPTY).get();
		if (null != json.get("otp") && null != user && user.getOtp().equals(json.get("otp"))) {
			user.setActive(Boolean.TRUE);
			result = usersRepository.saveAndFlush(user);
			result.setPassword(Strings.EMPTY);
			result.setOtp(Strings.EMPTY);
			return ResponseEntity.created(new URI("/api/user/approveotp/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(user.getPhone() + ServiceConstants.APPROVAL_ERROR);
	}
	
	@GetMapping("/logout/{phone}/{deviceId}")
	ResponseEntity<?> doLogout(@PathVariable String phone,@PathVariable String deviceId) {
		Optional<User> user = usersRepository.findByDeviceid(deviceId);
		user.get().setDeviceid(Strings.EMPTY);
		usersRepository.saveAndFlush(user.get());
		user.get().setPassword(Strings.EMPTY);
		return  ResponseEntity.ok().body(user.get());	
	}
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
