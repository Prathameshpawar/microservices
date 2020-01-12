package com.pp.restwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pp.restwebservices.controller.service.TodoHardcodedService;
import com.pp.restwebservices.pojo.Todo;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodosController {

	@Autowired
	private TodoHardcodedService todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return todoService.findAll();
	}
	
	@DeleteMapping("/users/{id}/todos")
	public boolean deleteById(@PathVariable Long id) {
		return todoService.deleteById(id);
	}
	
	@GetMapping("/users/{id}/todo")
	public Todo getTodo(@PathVariable Long id) {
		return todoService.findTodo(id);
	}
	
	@PutMapping("users/{id}/todos")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id,@RequestBody Todo todo){
		Todo updatedTodo = todoService.save(todo);
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
	}
	
	@PostMapping("users/todos")
	public ResponseEntity<Object> createTodo(@RequestBody Todo todo){
		Todo updatedTodo = todoService.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedTodo.getId())
				.toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
	
}
