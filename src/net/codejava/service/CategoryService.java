package net.codejava.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.Repository.CategoryRepository;
import net.codejava.model.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired CategoryRepository categoryRepository;
	
	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> listAll() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	public Category get(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public List<Category> listCategoryFromProductType (Long keyword) {
		return categoryRepository.listCategory(keyword);
	}
	public void ProductTypeService() {
	}
}
