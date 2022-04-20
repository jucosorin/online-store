package com.jucosorin.online.store.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceIntegrationTest {

	public static final String HEADPHONES_PRODUCT_TYPE = "Headphones";

	@Autowired
    ProductService productService;

	@Test
	@Sql(scripts = "classpath:scripts/insert-products.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "classpath:scripts/delete-products.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void quantityByProductTypeTest() {
		Assertions.assertThat(productService.getQuantityByProductType(HEADPHONES_PRODUCT_TYPE)).isEqualTo(4);
	}
}
