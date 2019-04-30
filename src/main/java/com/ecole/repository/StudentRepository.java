package com.ecole.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecole.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	
}