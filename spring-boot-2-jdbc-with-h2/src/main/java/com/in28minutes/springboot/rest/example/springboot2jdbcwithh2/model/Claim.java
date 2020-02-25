package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@TableGenerator (name="claim",initialValue=0, allocationSize=50)
public class Claim {
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    @Id
	private long id;
	private long code;
	private String name;
	private long id_employee;
	
	public Claim() {
		
	}
	
	public Claim(long code,String name, long id_employee) {
		this.code = code;
		this.name = name;
		this.id_employee = id_employee;
	}


	@Id
	@GeneratedValue (strategy = GenerationType.AUTO,generator="native")
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
	
	@Column (name ="code", nullable = false)
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	
	@Column (name="id_employee", nullable = false)
	public long getIdEmployee() {
		return id_employee;
	}
	public void setIdEmployee(long id_employee) {
		this.id_employee = id_employee;
	}	
}	
