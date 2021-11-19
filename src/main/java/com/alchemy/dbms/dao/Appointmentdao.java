package com.alchemy.dbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alchemy.dbms.model.Appointment;

@Repository
@Transactional
public class Appointmentdao {

	@Autowired
	JdbcTemplate jt;
	
	public void save(String concerns,String symptoms,String status,int sch_id) {
		String sql = "insert into Appointment(concerns,symptoms,schedule_id,status) values(?,?,?,?)";
		jt.update(sql,concerns,symptoms,sch_id,status);
	}
	
	public Appointment findAppointmentBySchedule(int sch_id) {
		String sql = "select * from Appointment where schedule_id='"+sch_id+"'";
		return jt.queryForObject(sql,new RowMapper<Appointment>() {
			public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException{
				Appointment appointment = new Appointment();
				appointment.setConcerns(rs.getString("concerns"));
				appointment.setSymptoms(rs.getString("symptoms"));
				appointment.setDiagnosis(rs.getString("diagnosis"));
				appointment.setPrescription(rs.getString("prescription"));
				appointment.setStatus(rs.getString("status"));
				return appointment;
			}
		});
	}
	
	public void updateStatus(String diagnosis, String prescription,String status,int sch_id) {
		String sql ="update Appointment SET diagnosis=?, prescription=?, status=? where schedule_id=?";
		jt.update(sql,diagnosis,prescription,status,sch_id);
	}
	
	public void deleteAppointmentBySchId(int sch_id) {
		String sql = "delete from Appointment where schedule_id=?";
		jt.update(sql,sch_id);
	}
}
