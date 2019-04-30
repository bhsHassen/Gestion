package com.ecole.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecole.entity.Student;
import com.ecole.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

		// Implementing Constructor based DI
		private StudentRepository repository;
		
		public StudentServiceImpl() {
			
		}
		
		@Autowired
		public StudentServiceImpl(StudentRepository repository) {
			super();
			this.repository = repository;
		}
		
	@Override
	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<Student>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Student getStudentById(Long id) {
		Student user = repository.findById(id).get();
		return user;
	}

	@Override
	public boolean saveStudent(Student user) {
		try {
			repository.save(user);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteStudentById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}

}
