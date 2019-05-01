package com.ecole.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecole.entity.Category;
import com.ecole.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

		// Implementing Constructor based DI
		private CategoryRepository repository;
		
		public CategoryServiceImpl() {
			
		}
		
		@Autowired
		public CategoryServiceImpl(CategoryRepository repository) {
			super();
			this.repository = repository;
		}
		
	@Override
	public List<Category> getAllCategorys() {
		List<Category> list = new ArrayList<Category>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Category getCategoryById(Long id) {
		Category user = repository.findById(id).get();
		return user;
	}

	@Override
	public boolean saveCategory(Category user) {
		try {
			repository.save(user);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteCategoryById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}

}
