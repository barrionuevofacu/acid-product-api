package com.inditex.productapi.infrastructure.adapter.out.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.productapi.domain.Product;
import com.inditex.productapi.domain.Stock;
import com.inditex.productapi.infrastructure.adapter.out.db.model.ProductEntity;
import com.inditex.productapi.infrastructure.adapter.out.db.model.StockEntity;
import com.inditex.productapi.infrastructure.adapter.out.db.repository.ProductRepository;

@SpringBootTest
class ProductDbAdapterTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductDbAdapter productDbAdapter;

	@Test
	public void testFindAll() {
		List<ProductEntity> productEntities = Arrays.asList(
				new ProductEntity("1", "V-NECH BASIC SHIRT", 100,
						Arrays.asList(new StockEntity("S", 4), new StockEntity("M", 9), new StockEntity("L", 0))),
				new ProductEntity("2", "CONTRASTING FABRIC T-SHIRT", 50,
						Arrays.asList(new StockEntity("S", 35), new StockEntity("M", 9), new StockEntity("L", 9))),
				new ProductEntity("3", "RAISED PRINT T-SHIRT", 80,
						Arrays.asList(new StockEntity("S", 20), new StockEntity("M", 2), new StockEntity("L", 20))));
		List<Product> expectedProducts = Arrays.asList(
				new Product("1", "V-NECH BASIC SHIRT", 100,
						Arrays.asList(new Stock("S", 4), new Stock("M", 9), new Stock("L", 0))),
				new Product("2", "CONTRASTING FABRIC T-SHIRT", 50,
						Arrays.asList(new Stock("S", 35), new Stock("M", 9), new Stock("L", 9))),
				new Product("3", "RAISED PRINT T-SHIRT", 80,
						Arrays.asList(new Stock("S", 20), new Stock("M", 2), new Stock("L", 20))));

		when(productRepository.findAll()).thenReturn(productEntities);
		
		when(modelMapper.map(any(ProductEntity.class), eq(Product.class))).thenAnswer(invocation -> {
			ProductEntity productEntity = invocation.getArgument(0);
			List<Stock> stockList = productEntity.getStock().stream().map(stockEnt -> new Stock(stockEnt.getSize(), stockEnt.getUnits()))
	                .collect(Collectors.toList());
			return new Product(productEntity.getIdProduct(), productEntity.getName(), productEntity.getSalesUnits(), stockList);
		});

		List<Product> actualProducts = productDbAdapter.findAll();

		assertEquals(expectedProducts.get(0).getIdProduct(), actualProducts.get(0).getIdProduct());
		assertEquals(expectedProducts.get(1).getIdProduct(), actualProducts.get(1).getIdProduct());
		assertEquals(expectedProducts.get(2).getIdProduct(), actualProducts.get(2).getIdProduct());
		verify(productRepository, times(1)).findAll();
		verify(modelMapper, times(productEntities.size())).map(any(ProductEntity.class), eq(Product.class));
	}

}
