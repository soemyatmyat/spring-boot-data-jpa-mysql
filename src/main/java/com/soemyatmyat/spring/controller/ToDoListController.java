package com.soemyatmyat.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soemyatmyat.spring.model.*;
import com.soemyatmyat.spring.repository.*;


@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ToDoListController {

	@Autowired
	ToDoListRepository todoListRepository;
	
	@PostMapping("/todos")
	public ResponseEntity<ToDo> createTutorial(@RequestBody ToDo todo) {
	    try {
	      ToDo _todo = todoListRepository
	          .save(new ToDo(todo.getTitle(), todo.getDescription(), false));
	      return new ResponseEntity<>(_todo, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/todos")
	public ResponseEntity<List<ToDo>> getAllToDos(@RequestParam(required=false) String title) {
		try {
			List<ToDo> todos=new ArrayList<ToDo>();
			
			if (title == null)
				todoListRepository.findAll().forEach(todos::add);
			else	
				todoListRepository.findByTitleContaining(title).forEach(todos::add);
			
			if (todos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(todos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<ToDo> getToDoById(@PathVariable("id") long id) {
	    Optional<ToDo> toDoData = todoListRepository.findById(id);

	    if (toDoData.isPresent()) {
	      return new ResponseEntity<>(toDoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<ToDo> updateToDoById(@PathVariable("id") long id, @RequestBody ToDo todo) {
		Optional<ToDo> toDoData=todoListRepository.findById(id);
		
		if (toDoData.isPresent()) {
			ToDo _todo=toDoData.get();
			_todo.setTitle(todo.getTitle());
			_todo.setDescription(todo.getDescription());
			_todo.setCompleted(todo.isCompleted());
			return new ResponseEntity<>(todoListRepository.save(_todo),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	 
}
