package com.alchemy.dbms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {

	@Id
	private String email;
	private String password;
	private String name;
	private String gender;
	private String address;
	
	@OneToMany(mappedBy = "patient")
	private Set<Schedule> schedules = new HashSet<>();
	
	@OneToMany(mappedBy = "patient")
	private Set<MedicalHistory> med_history = new HashSet<>();
	
	public Set<MedicalHistory> getMed_history() {
		return med_history;
	}
	public void setMed_history(Set<MedicalHistory> med_history) {
		this.med_history = med_history;
	}
	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	public Set getSchedules() {
		return schedules;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
