package com.pp.restwebservices.controller.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pp.restwebservices.pojo.Todo;

@Service
public class TodoHardcodedService {

	@Autowired
	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 0;
	static {
		todos.add(new Todo(++idCounter, "Nilesh", "learn to dance", new Date(), false));
		todos.add(new Todo(++idCounter, "Nilesh", "learn to speak", new Date(), false));
		todos.add(new Todo(++idCounter, "Rocky", "learn to code", new Date(), false));
	}

	public List<Todo> findAll() {
		return todos;
	}

	public boolean deleteById(Long id) {
		return todos.removeIf(todo -> id.equals(todo.getId()));
	}

	public Todo findTodo(Long id) {
		return todos.stream().filter(todo->id.equals(todo.getId())).findAny().orElse(null);
	}
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0  ) {
			todo.setId(++idCounter);
			
			todos.add(todo);
		}else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
		
	}
}
