package com.jucosorin.online.store;

import com.jucosorin.online.store.services.StockService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
class StockServiceIntegrationTest {

	public static final String HEADPHONES_PRODUCT_TYPE = "Headphones";

	@Autowired
	StockService stockService;

	@Test
	@Sql(scripts = "classpath:scripts/insert-products.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "classpath:scripts/delete-products.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void quantityByProductTypeTest() {
		Assertions.assertThat(stockService.getQuantityByProductType(HEADPHONES_PRODUCT_TYPE)).isEqualTo(4);
	}
}
