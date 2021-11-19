package com.alchemy.dbms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Doctor {

	@Id
	private String email;
	private String password;
	private String name;
	private String gender;
	private String speciality;
	
	@OneToMany(mappedBy = "doctor")
	private Set<Schedule> schedules = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "doctors_view_history", joinColumns = {@JoinColumn(name="email")},
	inverseJoinColumns = { @JoinColumn(name="history_id")})
	private Set<MedicalHistory> histories = new HashSet<>();
	
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Set<MedicalHistory> getHistories() {
		return histories;
	}
	public void setHistories(Set<MedicalHistory> histories) {
		this.histories = histories;
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
	
}
