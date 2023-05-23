package com.inditex.productapi.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.productapi.application.port.out.db.ProductDbPort;
import com.inditex.productapi.domain.Product;
import com.inditex.productapi.domain.Stock;

@SpringBootTest
public class ProductSortingServiceTest {

	@Mock
    private ProductDbPort productDbPort;

	@InjectMocks
    private ProductSortingService productSortingService;

    @Test
    public void testSortProducts() {
        Double salesWeight = 0.7;
        Double stockWeight = 0.3;

        Product product1 = new Product("1", "V-NECH BASIC SHIRT", 100, Arrays.asList(new Stock("S", 4), new Stock("M", 9), new Stock("L", 0))); // 70 + 1.95 = 71.95
        Product product2 = new Product("2", "CONTRASTING FABRIC T-SHIRT", 50, Arrays.asList(new Stock("S", 35), new Stock("M", 9), new Stock("L", 9))); // 35 + 5.3 = 40.3
        Product product3 = new Product("3", "RAISED PRINT T-SHIRT", 80,	Arrays.asList(new Stock("S", 20), new Stock("M", 2), new Stock("L", 20))); // 56 + 4.2 = 60.2
        List<Product> mockProducts = Arrays.asList(product1, product2, product3);

        when(productDbPort.findAll()).thenReturn(mockProducts);

        List<Product> sortedProducts = productSortingService.sortProducts(salesWeight, stockWeight);

        assertEquals(3, sortedProducts.size());
        assertEquals("V-NECH BASIC SHIRT", sortedProducts.get(0).getName());
        assertEquals("RAISED PRINT T-SHIRT", sortedProducts.get(1).getName());
    }
    
    @Test
    public void testSortProductsWith0UnitsInStock() {
        Double salesWeight = 0.7;
        Double stockWeight = 0.3;

        Product product1 = new Product("1", "V-NECH BASIC SHIRT", 100, Arrays.asList(new Stock("S", 0), new Stock("M", 0), new Stock("L", 0))); // 70  = 70
        Product product2 = new Product("2", "CONTRASTING FABRIC T-SHIRT", 50, Arrays.asList(new Stock("S", 35), new Stock("M", 9), new Stock("L", 9))); // 35 + 5.3 = 40.3
        Product product3 = new Product("3", "RAISED PRINT T-SHIRT", 95,	Arrays.asList(new Stock("S", 20), new Stock("M", 2), new Stock("L", 20))); // 66.5 + 4.2 = 70.7
        List<Product> mockProducts = Arrays.asList(product1, product2, product3);

        when(productDbPort.findAll()).thenReturn(mockProducts);

        List<Product> sortedProducts = productSortingService.sortProducts(salesWeight, stockWeight);

        assertEquals(3, sortedProducts.size());
        assertEquals("RAISED PRINT T-SHIRT", sortedProducts.get(0).getName());
        assertEquals("V-NECH BASIC SHIRT", sortedProducts.get(1).getName());
    }
}
