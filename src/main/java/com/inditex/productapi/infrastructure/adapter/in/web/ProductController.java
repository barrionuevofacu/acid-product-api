package com.inditex.productapi.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.productapi.application.ProductSortingService;
import com.inditex.productapi.application.port.in.web.ProductWebPort;
import com.inditex.productapi.domain.Product;

@RestController
public class ProductController implements ProductWebPort {

	@Autowired
	private ProductSortingService productSortingService;

	@Override
	public List<Product> getSortedProducts(Double salesWeight, Double stockWeight) {
		return productSortingService.sortProducts(salesWeight, stockWeight);
	}

}