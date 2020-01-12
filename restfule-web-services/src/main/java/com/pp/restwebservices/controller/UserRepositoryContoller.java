package com.pp.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.pp.restwebservices.repository.UserRepository;

@RestController
public class UserRepositoryContoller {

	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/jpa/user/{userId}")
	public User getUser(@PathVariable Integer userId) {
		Optional<User> findUser = userRepository.findById(userId);
		if (!findUser.isPresent()) {
			throw new UserNotFoundException("id-" + userId);
		}
		return findUser.get();
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
	
	@DeleteMapping(path = "/jpa/deleteUser/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
		 userRepository.deleteById(userId);
		return  ResponseEntity.ok().build();//(HttpStatus.NOT_FOUND);
	}
	

	@GetMapping(path = "/jpa/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping(path = "/jpa/user/createUser")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.saveAndFlush(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
