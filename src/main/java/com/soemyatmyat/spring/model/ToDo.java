package com.soemyatmyat.spring.model;

import javax.persistence.*;

@Entity
@Table(name="ToDos")
public class ToDo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="completed")
	private boolean completed;
	
	public ToDo() {
		
	}
	
	public ToDo(String title, String description, boolean completed) {
		this.title=title;
		this.description=description;
		this.completed=completed;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return "To_Do_List [id="+id+", title="+title
				+", desc="+description+", completed="+completed+"]";
	}
	
	
}
