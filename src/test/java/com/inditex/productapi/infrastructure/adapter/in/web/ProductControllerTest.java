package com.inditex.productapi.infrastructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.productapi.application.ProductSortingService;
import com.inditex.productapi.domain.Product;
import com.inditex.productapi.domain.Stock;

@SpringBootTest
public class ProductControllerTest {

	@Mock
	private ProductSortingService productSortingService;

	@InjectMocks
	private ProductController productController;
	
	@Test
	public void testGetSortedProducts() throws Exception {
		Double salesWeight = 0.7;
		Double stockWeight = 0.3;

		List<Product> expectedProducts = Arrays.asList(
				new Product("1", "V-NECH BASIC SHIRT", 100,
						Arrays.asList(new Stock("S", 4), new Stock("M", 9), new Stock("L", 0))), // 70 + 1.95 = 71.95
				new Product("2", "CONTRASTING FABRIC T-SHIRT", 50,
						Arrays.asList(new Stock("S", 35), new Stock("M", 9), new Stock("L", 9))), // 35 + 5.3 = 40.3
				new Product("3", "RAISED PRINT T-SHIRT", 80,
						Arrays.asList(new Stock("S", 20), new Stock("M", 2), new Stock("L", 20))) // 56 + 4.2 = 60.2
		);

        when(productSortingService.sortProducts(salesWeight, stockWeight)).thenReturn(expectedProducts);

        List<Product> actualProducts = productController.getSortedProducts(salesWeight, stockWeight);

        assertEquals(expectedProducts, actualProducts);
        verify(productSortingService, times(1)).sortProducts(salesWeight, stockWeight);

	}
}
