package com.inditex.productapi.infrastructure.adapter.out.db.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "products")
public class ProductEntity {
    
    @Getter
    @Setter
    private String idProduct;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Field("sales_units")
    private int salesUnits;
    
    @Getter
    private List<StockEntity> stock;
    
    public ProductEntity(){}
    
	public ProductEntity(String idProduct, String name, int salesUnits, List<StockEntity> stock) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.salesUnits = salesUnits;
		this.stock = stock;
	}

}
