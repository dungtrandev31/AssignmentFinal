package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.Repository.ProducttypeRepository;
import net.codejava.model.ProductType;

@Service
@Transactional
public class ProductTypeService {

		@Autowired ProducttypeRepository producttypeRepository;
		
		public void save(ProductType productType) {
			producttypeRepository.save(productType);
		}
		
		public List<ProductType> listAll() {
			return (List<ProductType>) producttypeRepository.findAll();
		}
		
		public ProductType get(Long id) {
			return producttypeRepository.findById(id).get();
		}
		
		public void delete(Long id) {
			producttypeRepository.deleteById(id);
		}
		public void editProdType(String producttype, Long id) {
			producttypeRepository.editProdType(producttype, id);
		}
}
