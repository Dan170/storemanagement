package com.storemanagement.service.services;

import com.storemanagement.jpa.repositories.PriceHistoryRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.mappers.PriceHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PriceHistoryServiceImpl implements PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;
    private final PriceHistoryMapper priceHistoryMapper = new PriceHistoryMapper();

    @Autowired
    public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public PriceHistoryDTO savePriceHistory(PriceHistoryDTO priceHistoryDTO) {
        priceHistoryDTO.setId(0);
        var priceHistoryDO = priceHistoryMapper.mapDtoToDo(priceHistoryDTO);
        var savedPriceHistory = priceHistoryRepository.save(priceHistoryDO);
        return priceHistoryMapper.mapDoToDto(savedPriceHistory);
    }
}
