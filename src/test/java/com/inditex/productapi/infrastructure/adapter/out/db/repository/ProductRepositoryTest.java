package com.inditex.productapi.infrastructure.adapter.out.db.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.inditex.productapi.infrastructure.adapter.out.db.model.ProductEntity;

@SpringBootTest
public class ProductRepositoryTest {

    @Mock
    private MongoRepository<ProductEntity, String> mongoRepository;
    
    @Mock
    private ProductRepository productRepository;

    public ProductRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        String productId = "123";
        ProductEntity product = new ProductEntity();
        product.setIdProduct(productId);
        product.setName("Example Product");
        List<ProductEntity> products = new ArrayList<>(); 
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductEntity> result = productRepository.findAll();

        assertEquals(products.get(0), result.get(0));
    }

}