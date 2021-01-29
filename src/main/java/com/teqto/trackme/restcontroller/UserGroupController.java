package com.teqto.trackme.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
@RequestMapping("/api/usergroup")
public class UserGroupController {

	private final Logger log = LoggerFactory.getLogger(UserGroupController.class);
	@Autowired
	private UserGroupRepository userGroupRepository;
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getUserGroupById(@PathVariable Integer id) throws URISyntaxException,NoSuchElementException{
		Optional<Usergroup> ug = userGroupRepository.findById(id);
		log.info("found userGroup with id" + id);
		return ug.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getgroupbyuserid/{userid}")
	ResponseEntity<?> getGroupByUserId(@PathVariable Integer userid)
			throws URISyntaxException, NoSuchElementException {
		Optional<List<Usergroup>> ug = userGroupRepository.findByUserid(userid);
		List<Integer> groupids = ug.get().stream().map(Usergroup::getGroupid).collect(Collectors.toList());
		Optional<List<Group>> groups = Optional.ofNullable((List<Group>)groupRepository.findByIdIn(groupids));
		return groups.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	public static Iterable<Integer> toIterable(int[] ints) {
	    return IntStream.of(ints).boxed().collect(Collectors.toList());
	}
	
	@GetMapping("/getuserbygroupid/{groupid}")
	ResponseEntity<?> getUserByGroupId(@PathVariable Integer groupid)
			throws URISyntaxException, NoSuchElementException {
		Optional<List<Usergroup>> ug = userGroupRepository.findAllByGroupid(groupid);
		List<Integer> userids = ug.get().stream().map(Usergroup::getUserid).collect(Collectors.toList());
		Optional<List<User>> users = Optional.ofNullable(usersRepository.findByIdIn(userids));
		return users.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/getUserGroupPendingApproval/{ownerid}")
	ResponseEntity<?> getUserGroupByOwnerIdPendingApproval(@PathVariable Integer ownerid) throws URISyntaxException,NoSuchElementException{
		Group g = groupRepository.findByOwnerid(ownerid).get().get(0);
		Optional<List<Usergroup>> ug = userGroupRepository.findByGroupid(g.getId());
		log.info("found userGroup with id" + ownerid);
		return ug.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	ResponseEntity<?> approveGroupAccess(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException{
		Optional<Usergroup> result = null;
		if(json.get("groupid") != null && json.get("userid") != null) {
			Usergroup ug = userGroupRepository.findByUserAndGroup(Integer.parseInt(json.get("groupid")), Integer.parseInt(json.get("userid"))).get().get(0);
			Integer loginUser = Integer.parseInt(json.get("loginuser"));
			if(groupRepository.findById(ug.getGroupid()).get().getOwnerid() == loginUser)
			{
			ug.setApproved(Boolean.TRUE);
			ug.setApprovedby(loginUser);
			result = Optional.of(userGroupRepository.save(ug));
			}
		}
		return result.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	ResponseEntity<?> createUserGroup(@Valid @RequestBody Usergroup ug,
			HttpServletRequest request) throws URISyntaxException,NoSuchElementException {
		Usergroup result = null;
		log.info("Request to create UserGroup : {}", ug);
		if (ug.getGroupid() > 0 && ug.getUserid() > 0 && !userGroupRepository.findByGroupOnwerAndUser(ug.getGroupid(), ug.getUserid()).isPresent()) {
			User user = usersRepository.findById(ug.getUserid()).get();
			ug.setUsername(user.getName());
			ug.setAvatar(user.getAvatar());
			ug.setCreatedby(ug.getUserid());
			ug.setCreatedon(LocalDate.now());
			ug.setOwner(Boolean.FALSE);
			ug.setApproved(Boolean.FALSE);
			result = userGroupRepository.saveAndFlush(ug);
			return ResponseEntity.created(new URI("/api/usergroup/create/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(ug.getGroupid() + ServiceConstants.USERGROUPEXISTS);
	}
	
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
