package com.ecole.service;

import java.util.List;

import com.ecole.entity.Category;

public interface CategoryService {

	public List<Category> getAllCategorys();
	public Category getCategoryById(Long id);
	public boolean saveCategory(Category user);
	public boolean deleteCategoryById(Long id);

}