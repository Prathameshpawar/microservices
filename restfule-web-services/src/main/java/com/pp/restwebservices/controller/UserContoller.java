package com.pp.restwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pp.restwebservices.dao.UserDAOServiceImpl;
import com.pp.restwebservices.exception.UserNotFoundException;
import com.pp.restwebservices.pojo.User;

@RestController
public class UserContoller {

	@Autowired
	private UserDAOServiceImpl userDaoServiceImpl;
	
	@GetMapping(path = "/user/{userId}")
	public User getUser(@PathVariable Integer userId) {
		User findUser = userDaoServiceImpl.findUser(userId);
		if (null == findUser) {
			throw new UserNotFoundException("id-" + userId);
		}
		return findUser;
	}
	
//	//HATEAOS implementation
//	@GetMapping(path = "/user/{userId}")
//	public EntityModel<User> getUser(@PathVariable Integer userId) {
//		User findUser = userDaoServiceImpl.findUser(userId);
//		EntityModel<User> entityModel=new EntityModel<>(findUser);
//		WebMvcLinkBuilder linkTO= linkTo(methodOn(this.getClass()).getUsers());
//		entityModel.add(linkTO.withRel("all-users"));
//		
//		if (null == findUser) {
//			throw new UserNotFoundException("id-" + userId);
//		}
//		return entityModel;
//	}
	
	@DeleteMapping(path = "/deleteUser/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
		boolean isUserDeleted = userDaoServiceImpl.deleteUser(userId);
		if (!isUserDeleted) {
			throw new UserNotFoundException("id-" + userId);
		}
		return  ResponseEntity.ok().build();//(HttpStatus.NOT_FOUND);
	}
	

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userDaoServiceImpl.findAll();
	}

	@PostMapping(path = "/user/createUser")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoServiceImpl.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
