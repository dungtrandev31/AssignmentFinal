package net.codejava.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.codejava.model.Category;
import net.codejava.model.Product;
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	
	@Query(value=" FROM Product  WHERE category.idcategory = :category")
	public List<Product> listProdCate(@Param("category") Long category) ;
	
	@Query(value="SELECT c FROM Product c WHERE c.id = :id")
	public List<Product> findbyid(@Param("id") Long id) ;
	
	@Query(value=" from Product WHERE category.idproducttype = :idproducttype")
	public List<Product> listProdProdType(@Param("idproducttype") Long idproducttype);
	
	@Query(value = "SELECT c FROM Product c WHERE c.productname LIKE '%' || :keyword || '%'"
			+ " OR c.id LIKE '%' || :keyword || '%'"
			+ " OR c.category.categoryname LIKE '%' || :keyword || '%'"
			+ " OR c.price LIKE '%' || :keyword || '%'")
	public List<Product> search(@Param("keyword") String keyword);

}
