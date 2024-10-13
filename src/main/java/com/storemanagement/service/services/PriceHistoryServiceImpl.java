package com.storemanagement.service.services;

import com.storemanagement.jpa.repositories.PriceHistoryRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.mappers.PriceHistoryMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PriceHistoryServiceImpl implements PriceHistoryService {

    private static final Logger LOGGER = LogManager.getLogger(PriceHistoryServiceImpl.class);

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
        var savedPriceHistoryDO = priceHistoryRepository.save(priceHistoryDO);
        var savedPriceHistory = priceHistoryMapper.mapDoToDto(savedPriceHistoryDO);

        LOGGER.info("Saved Price History with id [{}]", savedPriceHistory.getId());
        return savedPriceHistory;
    }
}
