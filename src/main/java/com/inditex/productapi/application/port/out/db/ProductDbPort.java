package com.inditex.productapi.application.port.out.db;

import java.util.List;

import com.inditex.productapi.domain.Product;

public interface ProductDbPort {

	List<Product> findAll();

}