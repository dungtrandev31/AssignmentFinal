package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.Repository.ProductRepository;
import net.codejava.model.Product;
@Service
@Transactional
public class ProductService {
	
	@Autowired ProductRepository productRepository;
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> listAll() {
		return (List<Product>) productRepository.findAll();
	}
	
	public Product get(Long id) {
		return productRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> listProdCate(long id) {
		return productRepository.listProdCate(id);
		
	}
	public List<Product> findbyid(Long id) {
		return productRepository.findbyid(id);
		
	}
	
	
	public List<Product> listProdType(Long id) {
		return productRepository.listProdProdType(id);
		
	}
	
	public List<Product> search(String keyword) {
		return productRepository.search(keyword);
	}

}
