package com.inditex.productapi.infrastructure.adapter.out.db;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inditex.productapi.application.port.out.db.ProductDbPort;
import com.inditex.productapi.domain.Product;
import com.inditex.productapi.infrastructure.adapter.out.db.model.ProductEntity;
import com.inditex.productapi.infrastructure.adapter.out.db.repository.ProductRepository;

@Service
public class ProductDbAdapter implements ProductDbPort {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Product> findAll() {
		List<ProductEntity> productsEntityList = productRepository.findAll();
		List<Product> products = new ArrayList<>();
		productsEntityList.forEach(pEntity -> {
			products.add(modelMapper.map(pEntity, Product.class));
		});
		
		return products;
	}
}