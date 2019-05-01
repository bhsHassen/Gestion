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

import com.ecole.entity.Teacher;
import com.ecole.service.TeacherService;

@Controller
public class TeacherController {
	// Constructor based Dependency Injection
	private TeacherService teacherService;

	public TeacherController() {

	}

	@Autowired
	public TeacherController(TeacherService userService) {
		this.teacherService = userService;
	}



	// Get All Teachers
	@RequestMapping(value = "/allTeachers", method = RequestMethod.POST)
	public ModelAndView displayAllTeacher() {
		System.out.println("Teacher Page Requested : All Teachers");
		ModelAndView mv = new ModelAndView();
		List<Teacher> userList = teacherService.getAllTeachers();
		mv.addObject("userList", userList);
		mv.setViewName("allTeachers");
		return mv;
	}

	@RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
	public ModelAndView displayNewTeacherForm() {
		ModelAndView mv = new ModelAndView("addTeacher");
		mv.addObject("headerMessage", "Add Teacher Details");
		mv.addObject("user", new Teacher());
		return mv;
	}

	@RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
	public ModelAndView saveNewTeacher(@ModelAttribute Teacher user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = teacherService.saveTeacher(user);
		if (isAdded) {
			mv.addObject("message", "New user successfully added");
		} else {
			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/editTeacher/{id}", method = RequestMethod.GET)
	public ModelAndView displayEditTeacherForm(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("/editTeacher");
		Teacher user = teacherService.getTeacherById(id);
		mv.addObject("headerMessage", "Edit Teacher Details");
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/editTeacher/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditedTeacher(@ModelAttribute Teacher user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = teacherService.saveTeacher(user);
		if (!isSaved) {

			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/deleteTeacher/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTeacherById(@PathVariable Long id) {
		boolean isDeleted = teacherService.deleteTeacherById(id);
		System.out.println("Teacher deletion respone: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;

	}

}
