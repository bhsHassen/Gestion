package com.ecole.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecole.entity.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
}