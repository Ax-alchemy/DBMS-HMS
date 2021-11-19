package com.alchemy.dbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alchemy.dbms.model.Appointment;
import com.alchemy.dbms.model.Doctor;
import com.alchemy.dbms.model.Patient;
import com.alchemy.dbms.model.Schedule;

@Repository
@Transactional
public class Scheduledao {

	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	private Doctordao doctordao;
	
	@Autowired
	private Appointmentdao appointmentdao;
	
	@Autowired
	private Userdao userdao;
	
	public void save(Date date,int slot,String doc_email,String pat_email) {
		Map<Integer,String> map = new HashMap<>();
		map.put(1,"10:00:00");
		map.put(2,"11:00:00");
		map.put(3,"12:00:00");
		map.put(4,"14:00:00");
		map.put(5,"15:00:00");
		String sql = "insert into Schedule(date,slot,doc_email,pat_email,start_time) values(?,?,?,?,?)";
		jt.update(sql,date,slot,doc_email,pat_email,map.get(slot));
	}
	
	public int findId(Date date,int slot,String doc_email) {
		String sql = "select id from Schedule where date=? and slot=? and doc_email=?";
		int id = jt.queryForObject(sql, Integer.class,date,slot,doc_email);
		return id;
	}
	
	public List<Schedule> findSchedules(String pat_email){
		String sql = "select * from Schedule where pat_email='"+ pat_email +"'";
		return jt.query(sql, new RowMapper<Schedule>() {
			public Schedule mapRow(ResultSet rs,int rowNum) throws SQLException {
				Schedule schedule = new Schedule();
				schedule.setDate(rs.getDate("date"));
				schedule.setStartTime(rs.getTime("start_time"));
				schedule.setId(rs.getInt("id"));
				
				String doc_email = rs.getString("doc_email");
				Doctor doctor = doctordao.findDoctor(doc_email);
				schedule.setDoctor(doctor);
				
				int id = rs.getInt("id");
				Appointment appointment = appointmentdao.findAppointmentBySchedule(id);
				schedule.setAppointment(appointment);
				
				return schedule;
			}
		});
	}
	
	public List<Schedule> findDoctorSchedules(String doc_email){
		String sql = "select * from Schedule where doc_email='"+ doc_email +"'";
		return jt.query(sql, new RowMapper<Schedule>() {
			public Schedule mapRow(ResultSet rs,int rowNum) throws SQLException {
				Schedule schedule = new Schedule();
				schedule.setDate(rs.getDate("date"));
				schedule.setStartTime(rs.getTime("start_time"));
				schedule.setId(rs.getInt("id"));
				
				String pat_email = rs.getString("pat_email");
				Patient patient = userdao.findpatient(pat_email);
				schedule.setPatient(patient);
				
//				int id = rs.getInt("id");
				Appointment appointment = appointmentdao.findAppointmentBySchedule(schedule.getId());
				schedule.setAppointment(appointment);
				
				return schedule;
			}
		});
	}
	
	public void deleteScheduleById(int sch_id) {
		String sql = "delete from Schedule where id=?";
		jt.update(sql,sch_id);
	}
}
