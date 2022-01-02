package com.soemyatmyat.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soemyatmyat.spring.model.ToDo;

public interface ToDoListRepository extends JpaRepository<ToDo, Long>{
	//returns all ToDos with completed having value as input completed
	List<ToDo> findByCompleted(boolean completed); 
	// returns all ToDos which title contains title.
	List<ToDo> findByTitleContaining(String title);
}
