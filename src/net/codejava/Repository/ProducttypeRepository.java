package net.codejava.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.model.ProductType;

public interface ProducttypeRepository extends CrudRepository<ProductType, Long>{
	
	@Transactional
	@Modifying
	@Query( value ="UPDATE ProductType p  SET p.producttype = :producttype WHERE p.id  = :id")
	void editProdType(@Param("producttype") String producttype ,@Param("id") long id);
	
}
