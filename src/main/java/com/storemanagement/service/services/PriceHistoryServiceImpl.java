package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import com.storemanagement.jpa.repositories.PriceHistoryRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.mappers.PriceHistoryMapper;
import com.storemanagement.service.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PriceHistoryServiceImpl implements PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;
    private final PriceHistoryMapper priceHistoryMapper = new PriceHistoryMapper();
    private final ProductMapper productMapper = new ProductMapper();

    @Autowired
    public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public PriceHistoryDTO savePriceHistory(PriceHistoryDTO priceHistoryDTO) {
        PriceHistoryDO priceHistoryDO = priceHistoryMapper.mapDtoToDo(priceHistoryDTO);
        priceHistoryDO.setProductDO(productMapper.mapDtoToDo(priceHistoryDTO.getProductDTO()));

        PriceHistoryDO savedPriceHistory = priceHistoryRepository.save(priceHistoryDO);
        return priceHistoryMapper.mapDoToDto(savedPriceHistory);
    }
}
