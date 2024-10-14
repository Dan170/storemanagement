package com.storemanagement.service.mappers;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import org.junit.jupiter.api.Test;

import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDO;
import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDTO;
import static com.storemanagement.utils.ProductUtils.INVALID_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceHistoryMapperUnitTest {

    private final PriceHistoryMapper mapper = new PriceHistoryMapper();

    @Test
    void testMapDoToDto() {
        // given
        final long id = 1;
        final double price = 10;
        final long productId = 3;

        PriceHistoryDO priceHistoryDO = createPriceHistoryDO(id, price, productId);
        PriceHistoryDTO expectedPriceHistoryDTO = createPriceHistoryDTO(id, price, productId);

        // when
        PriceHistoryDTO actualPriceHistoryDTO = mapper.mapDoToDto(priceHistoryDO);

        // then
        assertEquals(expectedPriceHistoryDTO.getId(), actualPriceHistoryDTO.getId());
        assertEquals(expectedPriceHistoryDTO.getPrice(), actualPriceHistoryDTO.getPrice());
        assertEquals(expectedPriceHistoryDTO.getProductId(), actualPriceHistoryDTO.getProductId());
    }

    @Test
    void testMapDtoToDo() {
        // given
        final long id = 1;
        final double price = 10;
        final long productId = 3;

        PriceHistoryDTO priceHistoryDTO = createPriceHistoryDTO(id, price, productId);
        PriceHistoryDO expectedPriceHistoryDO = createPriceHistoryDO(id, price, productId);

        // when
        PriceHistoryDO actualPriceHistoryDO = mapper.mapDtoToDo(priceHistoryDTO);

        // then
        assertEquals(expectedPriceHistoryDO.getId(), actualPriceHistoryDO.getId());
        assertEquals(expectedPriceHistoryDO.getPrice(), actualPriceHistoryDO.getPrice());
        assertEquals(expectedPriceHistoryDO.getProductId(), actualPriceHistoryDO.getProductId());
    }

    @Test
    void testMapNullDo() {
        // when
        PriceHistoryDTO actualPriceHistory = mapper.mapDoToDto(null);

        // then
        assertEquals(INVALID_ID, actualPriceHistory.getId());
    }

    @Test
    void testMapNullDto() {
        // when
        PriceHistoryDO actualPriceHistory = mapper.mapDtoToDo(null);

        // then
        assertEquals(INVALID_ID, actualPriceHistory.getId());
    }
}
