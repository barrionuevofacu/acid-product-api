package com.inditex.productapi.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inditex.productapi.application.port.out.db.ProductDbPort;
import com.inditex.productapi.domain.Product;
import com.inditex.productapi.domain.SalesScore;
import com.inditex.productapi.domain.ScoreStrategy;
import com.inditex.productapi.domain.StockScore;

@Service
public class ProductSortingService {
	
    @Autowired
	ProductDbPort productDbPort;
    
    public List<Product> sortProducts(Double salesWeight, Double stockWeight) {
		List<ScoreStrategy> criterios = new ArrayList<>();
		criterios.add(new SalesScore(salesWeight));
		criterios.add(new StockScore(stockWeight));

		List<Product> products = productDbPort.findAll();

        products.sort((p1, p2) -> {
            Double score1 = p1.calcularPuntuacionTotal(criterios);
            Double score2 = p2.calcularPuntuacionTotal(criterios);
            return Double.compare(score2, score1); 
        });

        return products;
    }
    
}