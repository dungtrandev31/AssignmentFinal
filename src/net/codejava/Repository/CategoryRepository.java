package net.codejava.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.codejava.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query(value ="SELECT c FROM Category c WHERE c.idproducttype = :idproducttype")
	public List<Category> listCategory(@Param("idproducttype") Long idproducttype) ;
	
	
	
}
