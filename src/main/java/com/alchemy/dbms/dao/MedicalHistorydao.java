package com.alchemy.dbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alchemy.dbms.model.MedicalHistory;
import com.alchemy.dbms.model.Patient;

@Repository
@Transactional
public class MedicalHistorydao {

	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	private Userdao userdao;
	
	public void save(Date date,String conditions,String medication,String surgeries,String pat_email) {
		String sql = "insert into Medical_History(date,conditions,medication,surgeries,pat_email) values(?,?,?,?,?)";
		jt.update(sql,date,conditions,medication,surgeries,pat_email);
	}
	
	public List<MedicalHistory> findHistoryOfPatient(String pat_email){
		String sql = "select * from Medical_History where pat_email='"+pat_email+"'";
		return jt.query(sql, new RowMapper<MedicalHistory>() {
			public MedicalHistory mapRow(ResultSet rs, int rowNum) throws SQLException{
				MedicalHistory medhistory = new MedicalHistory();
				medhistory.setId(rs.getInt("id"));
				medhistory.setDate(rs.getDate("date"));
				medhistory.setConditions(rs.getString("conditions"));
				medhistory.setMedication(rs.getString("medication"));
				medhistory.setSurgeries(rs.getString("surgeries"));
				
				Patient patient = userdao.findpatient(pat_email);
				medhistory.setPatient(patient);
				
				return medhistory;
			}
		});
	}
	
	public List<MedicalHistory> findAllHistory(){
		String sql = "select * from Medical_History order by pat_email";
		return jt.query(sql, new RowMapper<MedicalHistory>() {
			public MedicalHistory mapRow(ResultSet rs, int rowNum) throws SQLException{
				MedicalHistory medhistory = new MedicalHistory();
				medhistory.setId(rs.getInt("id"));
				medhistory.setDate(rs.getDate("date"));
				medhistory.setConditions(rs.getString("conditions"));
				medhistory.setMedication(rs.getString("medication"));
				medhistory.setSurgeries(rs.getString("surgeries"));
				
				String pat_email = rs.getString("pat_email");
				Patient patient = userdao.findpatient(pat_email);
				medhistory.setPatient(patient);
				
				return medhistory;
			}
		});
	}
	
	public void updateHistory(int id, String conditions,String medication,String surgeries) {
		String sql = "update Medical_History set conditions=?, medication=?, surgeries=? where id=?";
		jt.update(sql,conditions,medication,surgeries,id);
	}
}
