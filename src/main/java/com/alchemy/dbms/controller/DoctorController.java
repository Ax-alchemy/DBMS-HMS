package com.alchemy.dbms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alchemy.dbms.dao.Appointmentdao;
import com.alchemy.dbms.dao.Doctordao;
import com.alchemy.dbms.dao.MedicalHistorydao;
import com.alchemy.dbms.dao.Scheduledao;
import com.alchemy.dbms.dao.Userdao;
import com.alchemy.dbms.model.Doctor;
import com.alchemy.dbms.model.MedicalHistory;
import com.alchemy.dbms.model.Patient;
import com.alchemy.dbms.model.Schedule;

@Controller
public class DoctorController {
	
	@Autowired
	private Scheduledao scheduledao;
	
	@Autowired
	private Doctordao doctordao;
	
	@Autowired
	private Appointmentdao appointmentdao;
	
	@Autowired
	private MedicalHistorydao medicalhistorydao;
	
	@Autowired
	private Userdao userdao;

	@RequestMapping("/doctorappointments")
	public ModelAndView ViewAppointments(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		mv.setViewName("doctorappointments");
		
		List<Schedule> list = scheduledao.findDoctorSchedules(doc_email);
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	
	@PostMapping("/attendappointment")
	public ModelAndView AttendAppointment(String diagnosis, String prescription,int sch_id,String pat_email,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		appointmentdao.updateStatus(diagnosis, prescription, "checked", sch_id);
		
		mv.setViewName("recordhistory");
		
		mv.addObject("pat_email", pat_email);
		
		return mv;
	}
	
	
	@PostMapping("/addhistory")
	public ModelAndView AddHistory(String date,String conditions,String medication,String surgeries,String pat_email,HttpSession session) throws ParseException {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		Date dat = new SimpleDateFormat("dd/mm/yyyy").parse(date);
		medicalhistorydao.save(dat, conditions, medication, surgeries, pat_email);
		
		mv.setViewName("doctorappointments");
		
		
		List<Schedule> list = scheduledao.findDoctorSchedules(doc_email);
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@RequestMapping("/viewpatients")
	public ModelAndView ViewPatients(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		mv.setViewName("viewpatients");
		
		List<MedicalHistory> list = medicalhistorydao.findAllHistory();
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@PostMapping("/viewpatientbyemail")
	public ModelAndView ViewPatientByEmail(String pat_email, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		mv.setViewName("pathistorybydoctor");
		
		List<MedicalHistory> list = medicalhistorydao.findHistoryOfPatient(pat_email);
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@PostMapping("/canceldoctorappointment")
	public ModelAndView CancelDoctorAppointment(int sch_id,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		appointmentdao.deleteAppointmentBySchId(sch_id);
		scheduledao.deleteScheduleById(sch_id);
		
		mv.setViewName("doctorappointments");
		
		List<Schedule> list = scheduledao.findDoctorSchedules(doc_email);
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@PostMapping("/updatehistory")
	public ModelAndView UpdateHistory(int id,String conditions,String medication, String surgeries, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		medicalhistorydao.updateHistory(id,conditions,medication,surgeries);
		
		mv.setViewName("viewpatients");
		
		List<MedicalHistory> list = medicalhistorydao.findAllHistory();
		mv.addObject("list", list);
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
		
	}
	
	@RequestMapping("/doctorsettings")
	public ModelAndView PatientSettings(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String doc_email = session.getAttribute("doc_email").toString();
		
		mv.setViewName("doctorsettings");
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@PostMapping("/updatespeciality")
	public ModelAndView UpdateSpeciality(String doc_email,String speciality) {
		doctordao.updateSpeciality(doc_email,speciality);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("doctorsettings");
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@PostMapping("/updatedocpassword")
	public ModelAndView UpdateDocPassword(String doc_email,String password) {
		doctordao.updatePassword(doc_email,password);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("doctorsettings");
		
		Doctor doctor = doctordao.findDoctor(doc_email);
		mv.addObject("doctor", doctor);
		
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView Logout(HttpSession session) {
		session.removeAttribute("doc_email");
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/");
		
		return mv;
	}
}
