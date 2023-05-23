package com.inditex.productapi.integration;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetSortedProducts() {
		given().mockMvc(mockMvc).contentType(ContentType.JSON).param("salesWeight", 0.5).param("stockWeight", 0.5)
				.when().get("/products/sorted").then().statusCode(200).contentType(ContentType.JSON.toString())
			    .body("", hasSize(6))
			    .body("[0].idProduct", equalTo("5"))
			    .body("[1].idProduct", equalTo("1"))
			    .body("[2].idProduct", equalTo("3"))
			    .body("[3].idProduct", equalTo("2"))
			    .body("[4].idProduct", equalTo("6"))
			    .body("[5].idProduct", equalTo("4"));
	}
}