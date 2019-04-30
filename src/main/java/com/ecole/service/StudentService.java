package com.ecole.service;

import java.util.List;

import com.ecole.entity.Student;

public interface StudentService {

	public List<Student> getAllStudents();
	public Student getStudentById(Long id);
	public boolean saveStudent(Student user);
	public boolean deleteStudentById(Long id);

}