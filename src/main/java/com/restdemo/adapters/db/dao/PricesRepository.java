package com.restdemo.adapters.db.dao;

import java.util.Date;
import java.util.List;

import com.restdemo.adapters.db.entities.PricesDbo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends SpringDataGenericRepository<PricesDbo, Long>{

	/**
	 * Find prices between two dates and product id and brand id
	 * @param date
	 * @param productId
	 * @param brandId
	 * @return
	 */
	@Query(" FROM PricesDbo p " +
		   " WHERE (:date between p.startDate and p.endDate) or p.productId = :productId or  p.brandId = :brandId " +
		   " ORDER BY p.priority DESC, p.endDate ASC, p.startDate DESC ")
	public List<PricesDbo> findByDateAndProductIdAndBrandId(@Param("date") Date date,
															@Param("productId") Long productId,
															@Param("brandId") Long brandId);
	
}
