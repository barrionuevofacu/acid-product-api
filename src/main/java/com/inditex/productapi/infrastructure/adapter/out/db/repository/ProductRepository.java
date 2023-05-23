package com.inditex.productapi.infrastructure.adapter.out.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inditex.productapi.infrastructure.adapter.out.db.model.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}