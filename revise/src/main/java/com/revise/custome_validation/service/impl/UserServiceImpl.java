package com.revise.custome_validation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revise.custome_validation.entity.User;
import com.revise.custome_validation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	List<User> users;

	public UserServiceImpl() {
		users = new ArrayList<User>();
	}

	@Override
	public User createUser(User user) {
		users.add(user);
		if (users.size() == 1)
			return users.get(0);
		return users.get(users.size() - 1);
	}

}
