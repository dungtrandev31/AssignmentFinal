package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.Repository.CustomerRepository;
import net.codejava.model.Customer;

@Service
@Transactional
public class CustomerService {
	@Autowired CustomerRepository repo;
	
	public void save(Customer customer) {
		repo.save(customer);
	}
	
	public List<Customer> listAll() {
		return (List<Customer>) repo.findAll();
	}
	
	public Customer get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public void saveAndFlush(Customer customer) {
		repo.saveAndFlush(customer);
	}
	
	public <T> List<T> getlistOrder(long idcustomer) {
		
		return repo.listOrder(idcustomer);
		
	}
	public long getidLastCustomer() {
		 return repo.getidLastCustomer();
	}
	
//	public List<Customer> search(String keyword) {
//		return repo.search(keyword);
//	}
}
