package io.jawhar.productservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
	private Long id;
	private String name;
	private Long price;

	private Long boutiqueId;



	
	
}
