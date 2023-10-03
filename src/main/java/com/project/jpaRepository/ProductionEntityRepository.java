package com.project.jpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.ProductEntity;

public interface ProductionEntityRepository  extends JpaRepository<ProductEntity, String>{
    
	    // 페이징 처리를 위한 메서드
	@Query("SELECT pr.productionNum, p.pNum, p.productName, s.cNum, pr.dateRegister, pr.dateFinish, pr.amount, s.price, p.boxCount, pr.phase " +
		       "FROM ProductEntity p " +
		       "INNER JOIN p.productions  pr " +
		       "INNER JOIN p.sells  s " +
		       "WHERE  pr.product = p AND s.product = p")
	 	 Page<Object[]> findAllWithJoin(Pageable pageable);
	 	 
	
}
