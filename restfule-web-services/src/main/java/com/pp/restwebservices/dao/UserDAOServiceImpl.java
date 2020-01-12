package com.pp.restwebservices.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.pp.restwebservices.pojo.User;

@Repository
public class UserDAOServiceImpl {

	private static int countert = 0;
	public static List<User> users = new LinkedList<>();

	static {
		users.add(new User("Rahul", ++countert, 'M', new Date()));
		users.add(new User("Adam", ++countert, 'M', new Date()));
		users.add(new User("Eve", ++countert, 'F', new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User findUser(int id) {
		List<User> collect = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(collect)) {
			return null;
		}
		return collect.get(0);
	}

	public User saveUser(User user) {
		if (null == user.getId()) {
			user.setId(++countert);
		}
		user.setDate(new Date());
		users.add(user);
		return user;
	}

	public Boolean deleteUser(Integer userId) {
		boolean removeIf = users.removeIf(user->user.getId()==userId);
		return removeIf;
	}

}
