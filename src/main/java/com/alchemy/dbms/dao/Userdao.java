package com.alchemy.dbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alchemy.dbms.model.Patient;

@Repository
@Transactional
public class Userdao {

	@Autowired
	JdbcTemplate jt;
	
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void save(Patient pat) {
//		String pass = bCryptPasswordEncoder.encode(pat.getPassword());
		String sql = "insert into Patient values (?,?,?,?,?)";
		jt.update(sql,pat.getEmail(),pat.getAddress(),pat.getGender(),pat.getName(),pat.getPassword());
	}
	
	public boolean checkcred(String email,String pass) {
		String sql = "select count(*) from Patient where email=? and password=?";
		int k=jt.queryForObject(sql, Integer.class,email,pass);
		if(k==0) return false;
		else return true;
	}
	
	public Patient findpatient(String email) {
		String sql="select * from Patient where email='" + email + "'";
		return jt.queryForObject(sql, new RowMapper<Patient>(){
			public Patient mapRow(ResultSet rs,int rowNum) throws SQLException{
				Patient patient = new Patient();
				patient.setEmail(rs.getString("email"));
				patient.setName(rs.getString("name"));
				patient.setGender(rs.getString("gender"));
				patient.setAddress(rs.getString("address"));
				patient.setPassword(rs.getString("password"));
				return patient;
			}
		});
	}
	
	public void updateAddress(String pat_email,String address) {
		String sql = "update Patient set address=? where email=?";
		jt.update(sql,address,pat_email);
	}
	
	public void updatePassword(String pat_email,String password) {
		String sql = "update Patient set password=? where email=?";
		jt.update(sql,password,pat_email);
	}
}
