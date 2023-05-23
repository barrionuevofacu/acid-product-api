package com.inditex.productapi.domain;

public class StockScore implements ScoreStrategy {

	private Double weight;
	
	public StockScore(Double weight) {
		this.weight = weight;
	}
	
	@Override
	public Double calculateScore(Product product) {
		// Calcula la puntuación basada en el ratio de stock
        int totalStock = 0;
        int totalSizes = 0;

        for (Stock stock : product.getStock()) {
            totalStock += stock.getUnits();
            if (stock.getUnits() != 0) {
            	totalSizes++;
            }
        }

        if (totalSizes == 0) {
            return 0d; // Evita la división por cero
        }

        Double stockScore = (double) totalStock / totalSizes;

        return weight * stockScore;
	}

}
