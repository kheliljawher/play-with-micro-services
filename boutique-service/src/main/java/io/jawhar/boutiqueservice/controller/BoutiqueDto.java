package io.jawhar.boutiqueservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class BoutiqueDto {

    private Long id;
    private String name;

    private List<ProductDto> products;
}
