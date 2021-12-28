package io.jawhar.productservice.service.servicesImpl;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.jawhar.productservice.entity.BoutiqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jawhar.productservice.entity.Product;
import io.jawhar.productservice.repository.ProductRepository;
import io.jawhar.productservice.service.ProductService;
import io.jawhar.productservice.web.dto.ProductDto;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final RestTemplate restTemplate;
	

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {
		this.productRepository = productRepository;
		this.restTemplate = restTemplate;
	}

	public static ProductDto mapToDto(Product product) {
		if(product != null) {
			return new ProductDto(
					product.getId(),
					product.getName(),
					product.getPrice(),
					product.getBoutiqueId()
				);
		}
		return null;
	}

	@Override
	public ProductDto create(ProductDto productDto) {
		// TODO Auto-generated method stub
		ResponseEntity<BoutiqueDto> entrepotDtoResponseEntity = this.restTemplate.getForEntity("http://BOUTIQUE-SERVICE/api/v1/boutiques/{id}", BoutiqueDto.class, productDto.getBoutiqueId());
		return mapToDto(this.productRepository.save(
				new Product(productDto.getId(),
						productDto.getName(),
						productDto.getPrice(),
						entrepotDtoResponseEntity.getBody().getId())));
	}

	@Override
	public Set<ProductDto> findAll() {
		// TODO Auto-generated method stub
		return this.productRepository.findAll().stream()
				.map(ProductServiceImpl::mapToDto)
				.collect(Collectors.toSet());

	}

	@Override
	public ProductDto findById(Long id) {
		// TODO Auto-generated method stub
		return this.productRepository.findById(id).map(ProductServiceImpl::mapToDto).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.productRepository.deleteById(id);
	}

	@Override
	public Set<ProductDto> findAllByBoutiqueId(Long id) {
		return this.productRepository.findAllByboutiqueId(id).stream()
				.map(ProductServiceImpl::mapToDto)
				.collect(Collectors.toSet());
	}

}
