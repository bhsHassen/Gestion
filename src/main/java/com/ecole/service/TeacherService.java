package com.ecole.service;

import java.util.List;

import com.ecole.entity.Teacher;

public interface TeacherService {

	public List<Teacher> getAllTeachers();
	public Teacher getTeacherById(Long id);
	public boolean saveTeacher(Teacher user);
	public boolean deleteTeacherById(Long id);

}