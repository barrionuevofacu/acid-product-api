package com.inditex.productapi.domain;

import lombok.Getter;
import lombok.Setter;

public class Stock {

	@Setter
	@Getter
	private String size;

	@Setter
	@Getter
	private int units;

	public Stock() {}
	
	public Stock(String size, int units) {
		super();
		this.size = size;
		this.units = units;
	}
	
}
