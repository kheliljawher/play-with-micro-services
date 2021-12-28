package io.jawhar.productservice.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jawhar.productservice.service.ProductService;
import io.jawhar.productservice.web.dto.ProductDto;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
	
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public Set<ProductDto> findAll(){
		return this.productService.findAll();
	}

	@GetMapping(value = "/boutiques/{id}")
	public Set<ProductDto> findAllByBoutiqueId(@PathVariable Long id){
		return this.productService.findAllByBoutiqueId(id);
	}
	
	@GetMapping("/{id}")
	public ProductDto findById(@PathVariable Long id) {
		return this.productService.findById(id);
	}
	
	@PostMapping
	public ProductDto create(@RequestBody ProductDto product)
	{
		return this.productService.create(product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.productService.deleteById(id);
	}

}
