package com.inditex.productapi.domain;

public class SalesScore implements ScoreStrategy {

	private Double weight;
	
	public SalesScore(Double weight) {
		this.weight = weight;
	}
	
	@Override
	public Double calculateScore(Product product) {
		return weight * product.getSalesUnits();
	}

}
