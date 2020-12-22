package com.teqto.trackme.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
import com.teqto.trackme.repository.GroupRepository;
import com.teqto.trackme.repository.UserGroupRepository;
import com.teqto.trackme.repository.UsersRepository;



/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/group")
public class GroupController {

	private final Logger log = LoggerFactory.getLogger(GroupController.class);
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserGroupRepository userGroupRepository;
	@Autowired
	private UsersRepository usersRepository;
//	@Autowired
//	private TransectionRepository transectionRepository;
//	@Autowired
//	private EmailInterface emailUtils;

	@Autowired
	private Environment environment;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getGroupById(@PathVariable Integer id) throws URISyntaxException,NoSuchElementException{
		Optional<Group> group = groupRepository.findById(id);
		log.info("found Group with id" + id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/findByName", method = RequestMethod.POST)
	ResponseEntity<?> findByName(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException{
		Optional<List<Group>> groupList = groupRepository.findByName(json.get(ServiceConstants.GROUPNAME));
		log.info("found Group with name count" + groupList.get().size());
		return groupList.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/findByOwner", method = RequestMethod.POST)
	ResponseEntity<?> findByOwner(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException{
		Optional<List<Group>> groupList = groupRepository.findByOwnerid(Integer.valueOf(json.get(ServiceConstants.GROUPNAME)));
		log.info("found Group with owner count" + groupList.get().size());
		return groupList.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/findByPhone", method = RequestMethod.POST)
	ResponseEntity<?> findByPhone(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException{
		Optional<List<Group>> groupList = groupRepository.findByPhone(json.get(ServiceConstants.PHONENO));
		log.info("found Group with name" + groupList.get().size());
		return groupList.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	ResponseEntity<?> createGroup(@Valid @RequestBody Group group,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException {
		Group result = null;
		log.info("Request to create Group : {}", group);
		if (group.getOwnerid() > 0 && null != group.getName() && !groupRepository.findByOwnerid(group.getOwnerid()).isPresent()) {
			Optional<User> owner = usersRepository.findById(group.getOwnerid());
			String ownerName = "("+owner.get().getName()+"-"+owner.get().getContact()+")";
			group.setName(group.getName()+ownerName);
			group.setPhone(owner.get().getContact());
			group.setCreatedon(LocalDate.now());
			result = groupRepository.saveAndFlush(group);
			Usergroup ug = new Usergroup(result.getOwnerid(),result.getId(),LocalDate.now(),result.getOwnerid());
			ug.setOwner(Boolean.TRUE);
			ug.setApprovedby(result.getOwnerid());
			ug.setApproved(Boolean.TRUE);
			userGroupRepository.saveAndFlush(ug);
			return ResponseEntity.created(new URI("/api/group/create/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(group.getName() + ServiceConstants.GROUPEXISTS);
	}
	
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
