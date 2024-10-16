package com.storemanagement.service.services;

import com.storemanagement.jpa.repositories.PriceHistoryRepository;
import org.junit.jupiter.api.Test;

import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDO;
import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceHistoryServiceUnitTest {

    private final PriceHistoryRepository repository = mock(PriceHistoryRepository.class);
    private final PriceHistoryService service = new PriceHistoryServiceImpl(repository);

    @Test
    void testSavePriceHistory() {
        // given
        final long id = 1;
        final long productId = 2;
        final double price = 15;
        var expectedPriceHistoryDO = createPriceHistoryDO(id, price, productId);
        var expectedPriceHistoryDTO = createPriceHistoryDTO(id, price, productId);
        var priceHistoryDTO = createPriceHistoryDTO(id, price, productId);
        when(repository.save(any())).thenReturn(expectedPriceHistoryDO);

        // when
        var actualPriceHistoryDTO = service.savePriceHistory(priceHistoryDTO);

        // then
        assertEquals(expectedPriceHistoryDTO.getId(), actualPriceHistoryDTO.getId());
        assertEquals(expectedPriceHistoryDTO.getPrice(), actualPriceHistoryDTO.getPrice());
        assertEquals(expectedPriceHistoryDTO.getProductId(), actualPriceHistoryDTO.getProductId());
    }
}
