package com.cavoli.sales.repo;
import com.cavoli.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository<Sales, Long> {

	Sales findBySaleId(Long userId);
}
