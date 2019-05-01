package com.ecole.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecole.entity.Teacher;
import com.ecole.repository.TeacherRepository;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

		// Implementing Constructor based DI
		private TeacherRepository repository;
		
		public TeacherServiceImpl() {
			
		}
		
		@Autowired
		public TeacherServiceImpl(TeacherRepository repository) {
			super();
			this.repository = repository;
		}
		
	@Override
	public List<Teacher> getAllTeachers() {
		List<Teacher> list = new ArrayList<Teacher>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Teacher getTeacherById(Long id) {
		Teacher user = repository.findById(id).get();
		return user;
	}

	@Override
	public boolean saveTeacher(Teacher user) {
		try {
			repository.save(user);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteTeacherById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}

}
