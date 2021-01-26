package com.teqto.trackme.utils;

import org.apache.logging.log4j.util.Strings;

import com.teqto.trackme.model.User;

public class UsersUtils {

	public static User getUserDTO(User user) {
	user.setPassword(Strings.EMPTY);
	user.setDeviceid(Strings.EMPTY);
	user.setOtp(Strings.EMPTY);
	return user;
	}
	
}
