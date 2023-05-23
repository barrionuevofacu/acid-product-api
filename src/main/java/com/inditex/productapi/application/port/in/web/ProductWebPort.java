package com.inditex.productapi.application.port.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inditex.productapi.domain.Product;

@RequestMapping("/products")
public interface ProductWebPort {

	@GetMapping("/sorted")
	List<Product> getSortedProducts(@RequestParam(name = "salesWeight", defaultValue = "0.0") Double salesWeight,
			@RequestParam(name = "stockWeight", defaultValue = "0.0") Double stockWeight); 

}