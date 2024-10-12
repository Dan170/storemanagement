package com.storemanagement.jpa.repositories;

import com.storemanagement.jpa.entities.ProductDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductDO, Long> {
}
