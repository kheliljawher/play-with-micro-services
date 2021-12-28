package io.jawhar.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.jawhar.productservice.entity.Product;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Set<Product> findAllByboutiqueId(Long id);

}
