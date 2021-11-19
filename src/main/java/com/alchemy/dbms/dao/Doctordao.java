package com.alchemy.dbms.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alchemy.dbms.model.Doctor;

@Repository
@Transactional
public class Doctordao {

	@Autowired
	JdbcTemplate jt;
	
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void save(Doctor doc) {
//		String pass = bCryptPasswordEncoder.encode(pat.getPassword());
		String sql = "insert into Doctor values (?,?,?,?,?)";
		jt.update(sql,doc.getEmail(),doc.getGender(),doc.getName(),doc.getPassword(),doc.getSpeciality());
	}
	
	public boolean checkcred(String email,String pass) {
		String sql = "select count(*) from Doctor where email=? and password=?";
		int k=jt.queryForObject(sql, Integer.class,email,pass);
		if(k==0) return false;
		else{
			return true;
		}
	}
	
	public Doctor findDoctor(String email) {
		String sql = "select * from Doctor where email='" + email + "'";
		return jt.queryForObject(sql, new RowMapper<Doctor>(){
			public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException{
				Doctor doctor = new Doctor();
				doctor.setEmail(rs.getString("email"));
				doctor.setName(rs.getString("name"));
				doctor.setGender(rs.getString("gender"));
				doctor.setSpeciality(rs.getString("speciality"));
				doctor.setPassword(rs.getString("password"));
				return doctor;
			}
		});
	}
	
	public List<Doctor> getAllDoctors(){
		String sql ="select * from Doctor";
		return jt.query(sql, new RowMapper<Doctor>() {
			public Doctor mapRow(ResultSet rs,int rowNum) throws SQLException{
				Doctor doctor = new Doctor();
				doctor.setEmail(rs.getString("email"));
				doctor.setName(rs.getString("name"));
				doctor.setGender(rs.getString("gender"));
				doctor.setSpeciality(rs.getString("speciality"));
				return doctor;
			}
		});
	}
	
	public List<Integer> getSlots(String doc_email, Date date){
		String sql = "select * from Schedule where doc_email='"+doc_email+"' and date='"+date+"'";
		return jt.query(sql, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs,int rowNum) throws SQLException{
				return rs.getInt("slot");
			}
		});
	}
	
	public void updateSpeciality(String doc_email,String speciality) {
		String sql = "update Doctor set speciality=? where email=?";
		jt.update(sql,speciality,doc_email);
	}
	
	public void updatePassword(String doc_email,String password) {
		String sql = "update Doctor set password=? where email=?";
		jt.update(sql,password,doc_email);
	}
}
