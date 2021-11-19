package com.alchemy.dbms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alchemy.dbms.dao.Doctordao;
import com.alchemy.dbms.dao.Userdao;
import com.alchemy.dbms.model.Doctor;
import com.alchemy.dbms.model.Patient;

@Controller
public class HomeController {

	@Autowired
	private Userdao userdao;
	
	@Autowired
	private Doctordao doctordao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/patsignin")
	public String PatSignIn() {
		return "patsignin";
	}
	
	@PostMapping("/loginuser")
	public ModelAndView Userlogin(String email,String password,RedirectAttributes redirectattr,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(userdao.checkcred(email,password)) {
			Patient patient = userdao.findpatient(email);
			mv.setViewName("patdashboard");
			mv.addObject(patient);
			session.setAttribute("pat_email", email);
			return mv;
		}
		else {
			mv.setViewName("patsignin");
			mv.addObject("message", "Wrong Username or Password");
			return mv;
		}
	}
	
	@RequestMapping("/patdashboard")
	public ModelAndView PatientDashboard(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("pat_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String email = session.getAttribute("pat_email").toString();
		
		mv.setViewName("patdashboard");
		
		Patient patient = userdao.findpatient(email);
		mv.addObject(patient);
		return mv;
	}
	
	
	@RequestMapping("/patregistration")
	public String PatRegistration() {
		return "patregistration";
	}
	
	@PostMapping("/adduser")
	public String AddUser(Patient patient,HttpSession session) {
		session.setAttribute("pat_email", patient.getEmail());
		userdao.save(patient);
		return "patdashboard";
	}
	
	@RequestMapping("/docsignin")
	public String DocSignIn() {
		return "docsignin";
	}
	
	@PostMapping("/logindoctor")
	public ModelAndView Doctorlogin(String email,String password,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(doctordao.checkcred(email,password)) {
			Doctor doctor = doctordao.findDoctor(email);
			session.setAttribute("doc_email", email);
			mv.setViewName("docdashboard");
			mv.addObject(doctor);
			return mv;
		}
		else {
			mv.setViewName("docsignin");
			mv.addObject("message", "Wrong Username or Password");
			return mv;
		}
	}
	
	@RequestMapping("/docregistration")
	public String DocRegistration() {
		return "docregistration";
	}
	
	@PostMapping("/adddoctor")
	public String AddDoctor(Doctor doctor,HttpSession session) {
		session.setAttribute("doc_email", doctor.getEmail());
		doctordao.save(doctor);
		return "docdashboard";
	}
	
	@RequestMapping("/docdashboard")
	public ModelAndView DoctorDashboard(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("doc_email")==null) {
			mv.setViewName("home");
			return mv;
		}
		
		String email = session.getAttribute("doc_email").toString();
		
		mv.setViewName("docdashboard");
		
		Doctor doctor = doctordao.findDoctor(email);
		mv.addObject(doctor);
		return mv;
	}
}
