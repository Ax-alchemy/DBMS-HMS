package com.alchemy.dbms.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class PatientController {

	@Autowired
	private Doctordao doctordao;
	
	@Autowired
	private Userdao userdao;
	
	@Autowired
	private Scheduledao scheduledao;
	
	@Autowired
	private Appointmentdao appointmentdao;
	
	@Autowired
	private MedicalHistorydao medicalhistorydao;
	
	@RequestMapping("/viewdoctors")
	public ModelAndView BookAppointment(HttpSession session) {
		ModelAndView mv=new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		mv.setViewName("viewdoctors");
		List<Doctor> list= doctordao.getAllDoctors();
		mv.addObject("list",list);
		return mv;
	}
	
	@PostMapping("/viewslots")
	public ModelAndView ViewSlots(@RequestParam("app_date") Date date, @RequestParam("doc_email") String doc_email,
			HttpSession session) {
		ModelAndView mv=new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		List<Integer> slots= doctordao.getSlots(doc_email,date);
//		System.out.println(doc_email);
		List<Integer> avl_slots = new ArrayList<Integer>();
		for(int i=1;i<=5;i++) {
			if(!slots.contains(i))
				avl_slots.add(i);
		}
		Map<Integer,String> map = new HashMap<>();
		map.put(1,"10:00:00");
		map.put(2,"11:00:00");
		map.put(3,"12:00:00");
		map.put(4,"14:00:00");
		map.put(5,"15:00:00");
		if(avl_slots.size()>0) {
			mv.setViewName("bookslot");
			mv.addObject("avl_slots", avl_slots);
			mv.addObject("doc_email", doc_email);
			mv.addObject("date", date);
			mv.addObject("map", map);
			return mv;
		}
		else {
			mv.addObject("msg", "No slots available for your specified doctor and date. Please select another options.");
			mv.setViewName("viewdoctors");
			List<Doctor> list= doctordao.getAllDoctors();
			mv.addObject("list",list);
			return mv;
		}
	}
	
	@PostMapping("/bookslot")
	public ModelAndView BookSlot(Date date,String doc_email,int slot,String concerns,String symptoms,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String pat_email = session.getAttribute("pat_email").toString();
		scheduledao.save(date, slot, doc_email, pat_email);
		int sch_id = scheduledao.findId(date, slot,doc_email);
		appointmentdao.save(concerns, symptoms, "pending",sch_id);
		
		mv.setViewName("patientappointments");
		
		List<Schedule> list = scheduledao.findSchedules(pat_email);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/patientappointments")
	public ModelAndView PatientAppointments(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String pat_email = session.getAttribute("pat_email").toString();
//		System.out.println(pat_email);
		
		mv.setViewName("patientappointments");
		
		List<Schedule> list = scheduledao.findSchedules(pat_email);
		mv.addObject("list", list);
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@RequestMapping("/pathistory")
	public ModelAndView PatientHistory(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String pat_email =session.getAttribute("pat_email").toString();
		
		mv.setViewName("pathistory");
		
		List<MedicalHistory> list = medicalhistorydao.findHistoryOfPatient(pat_email);
		mv.addObject("list", list);
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@PostMapping("/cancelappointment")
	public ModelAndView CancelAppointment(int sch_id,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String pat_email = session.getAttribute("pat_email").toString();
//		System.out.println(pat_email);
		appointmentdao.deleteAppointmentBySchId(sch_id);
		scheduledao.deleteScheduleById(sch_id);
		
		mv.setViewName("patientappointments");
		
		List<Schedule> list = scheduledao.findSchedules(pat_email);
		mv.addObject("list", list);
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@RequestMapping("/patientsettings")
	public ModelAndView PatientSettings(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String pat_email = session.getAttribute("pat_email").toString();
		
		mv.setViewName("patientsettings");
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@PostMapping("/updateaddress")
	public ModelAndView UpdateAddress(String pat_email,String address) {
		userdao.updateAddress(pat_email,address);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patientsettings");
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@PostMapping("/updatepassword")
	public ModelAndView UpdatePassword(String pat_email,String password) {
		userdao.updatePassword(pat_email,password);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patientsettings");
		
		Patient patient = userdao.findpatient(pat_email);
		mv.addObject("patient", patient);
		
		return mv;
	}
	
	@RequestMapping("/patlogout")
	public ModelAndView PatientLogout(HttpSession session) {
		session.removeAttribute("pat_email");
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		
		return mv;
	}
}
