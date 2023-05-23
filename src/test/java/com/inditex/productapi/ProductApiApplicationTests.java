package com.inditex.productapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApiApplicationTests {

	@Test
    public void mainMethodDoesNotThrowException() {
        ProductApiApplication.main(new String[]{});
    }

}
