package io.jawhar.productservice.service;

import java.util.Set;

import io.jawhar.productservice.web.dto.ProductDto;

public interface ProductService {
	
	ProductDto create(ProductDto productdto);
	Set<ProductDto> findAll();
	ProductDto findById(Long id);
	void deleteById(Long id);

	Set<ProductDto> findAllByBoutiqueId(Long id);

	

}
