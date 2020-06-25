package net.codejava.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.codejava.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
	@Query(value ="select o.id FROM Customer c join c.orderInfos o where c.idcustomer= :idcustomer")
	public <T> List<T> listOrder(@Param("idcustomer") Long idcustomer) ;

		
	String sql = "SELECT TOP 1 c.id FROM Customer AS c ORDER BY c.id DESC";
	
	@Query(nativeQuery = true, value ="SELECT TOP 1 c.idcustomer FROM Customer AS c ORDER BY c.idcustomer DESC")
	public long getidLastCustomer() ;
	
	

	
}
