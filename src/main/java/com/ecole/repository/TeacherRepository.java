package com.ecole.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecole.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>{
	
}