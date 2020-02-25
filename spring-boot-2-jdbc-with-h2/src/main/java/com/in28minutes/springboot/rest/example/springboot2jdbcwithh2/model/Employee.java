package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@TableGenerator (name="employees",initialValue=0, allocationSize=50)

public class Employee {
	@GeneratedValue(strategy=GenerationType.TABLE, generator="native")
    @Id
	private long id;
	private String name;
	private String surname;
	private String email;
	
	public Employee() {
		
	}
	
	public Employee(String name,String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	@Column (name = "name", nullable = false)
	public String getName() {
		return name;
    }
	public void setName(String name) {
		this.name = name;
	}
	
	@Column (name ="surname", nullable = false)
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Column (name ="email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}	
