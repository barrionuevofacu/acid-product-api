package com.inditex.productapi.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Product {
	@Setter
	@Getter
	private String idProduct;
	
	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private int salesUnits;

	@Setter
	@Getter
	private List<Stock> stock;
	
	public Product() {}
	
	public Product(String idProduct, String name, int salesUnits, List<Stock> stock) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.salesUnits = salesUnits;
		this.stock = stock;
	}
    
    public double calcularPuntuacionTotal(List<ScoreStrategy> criterios) {
    	double puntuacionTotal = 0;

        for (ScoreStrategy criterio : criterios) {
            puntuacionTotal += criterio.calculateScore(this);
        }

        return puntuacionTotal;
    }
	
}
