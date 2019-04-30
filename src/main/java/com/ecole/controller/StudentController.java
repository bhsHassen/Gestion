package com.ecole.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecole.entity.Student;
import com.ecole.service.StudentService;

@Controller
public class StudentController {
	// Constructor based Dependency Injection
	private StudentService StudentService;
	

	public StudentController() {

	}

	@Autowired
	public StudentController(StudentService userService) {
		this.StudentService = userService;
	}


	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView hello(HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	// Get All Students
	@RequestMapping(value = "/allStudents", method = RequestMethod.POST)
	public ModelAndView displayAllStudent() {
		System.out.println("Student Page Requested : All Students");
		ModelAndView mv = new ModelAndView();
		List<Student> userList = StudentService.getAllStudents();
		mv.addObject("userList", userList);
		mv.setViewName("allStudents");
		return mv;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public ModelAndView displayNewStudentForm() {
		ModelAndView mv = new ModelAndView("addStudent");
		mv.addObject("headerMessage", "Add Student Details");
		mv.addObject("user", new Student());
		return mv;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView saveNewStudent(@ModelAttribute Student user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = StudentService.saveStudent(user);
		if (isAdded) {
			mv.addObject("message", "New user successfully added");
		} else {
			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/editStudent/{id}", method = RequestMethod.GET)
	public ModelAndView displayEditStudentForm(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("/editStudent");
		Student user = StudentService.getStudentById(id);
		mv.addObject("headerMessage", "Edit Student Details");
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/editStudent/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditedStudent(@ModelAttribute Student user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = StudentService.saveStudent(user);
		if (!isSaved) {

			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStudentById(@PathVariable Long id) {
		boolean isDeleted = StudentService.deleteStudentById(id);
		System.out.println("Student deletion respone: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;

	}

}
