package com.storemanagement.jpa.repositories;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceHistoryRepository extends CrudRepository<PriceHistoryDO, Long> {
}
