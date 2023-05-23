package com.inditex.productapi.infrastructure.adapter.out.db.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "products")
public class StockEntity {
    
    @Getter
    private String size;
   
    @Getter
    private int units;

	public StockEntity(String size, int units) {
		super();
		this.size = size;
		this.units = units;
	}
    
}
