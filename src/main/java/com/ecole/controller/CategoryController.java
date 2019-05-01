package com.ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecole.entity.Category;
import com.ecole.service.CategoryService;

@Controller
public class CategoryController {
	// Constructor based Dependency Injection
	private CategoryService categoryService;
	

	public CategoryController() {

	}

	@Autowired
	public CategoryController(CategoryService userService) {
		this.categoryService = userService;
	}



	// Get All Categorys
	@RequestMapping(value = "/allCategories", method = RequestMethod.POST)
	public ModelAndView displayAllCategory() {
		System.out.println("Category Page Requested : All Categorys");
		ModelAndView mv = new ModelAndView();
		List<Category> userList = categoryService.getAllCategorys();
		mv.addObject("userList", userList);
		mv.setViewName("allCategories");
		return mv;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView displayNewCategoryForm() {
		ModelAndView mv = new ModelAndView("addCategory");
		mv.addObject("headerMessage", "Add Category Details");
		mv.addObject("user", new Category());
		return mv;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView saveNewCategory(@ModelAttribute Category user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = categoryService.saveCategory(user);
		if (isAdded) {
			mv.addObject("message", "New user successfully added");
		} else {
			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/editCategory/{id}", method = RequestMethod.GET)
	public ModelAndView displayEditCategoryForm(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("/editCategory");
		Category user = categoryService.getCategoryById(id);
		mv.addObject("headerMessage", "Edit Category Details");
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/editCategory/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditedCategory(@ModelAttribute Category user, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = categoryService.saveCategory(user);
		if (!isSaved) {

			return new ModelAndView("error");
		}

		return mv;
	}

	@RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCategoryById(@PathVariable Long id) {
		boolean isDeleted = categoryService.deleteCategoryById(id);
		System.out.println("Category deletion respone: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;

	}

}
