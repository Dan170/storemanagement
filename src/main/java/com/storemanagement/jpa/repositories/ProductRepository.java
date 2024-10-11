package com.storemanagement.jpa.repositories;

import com.storemanagement.jpa.entities.ProductDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDO, Long> {
}
