package io.jawhar.boutiqueservice.service;

import io.jawhar.boutiqueservice.controller.BoutiqueDto;
import io.jawhar.boutiqueservice.controller.ProductDto;
import io.jawhar.boutiqueservice.entity.Boutique;
import io.jawhar.boutiqueservice.repository.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoutiqueService {

    private final BoutiqueRepository boutiqueRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public BoutiqueService(BoutiqueRepository boutiqueRepository, RestTemplate restTemplate) {
        this.boutiqueRepository = boutiqueRepository;
        this.restTemplate = restTemplate;
    }

    public static BoutiqueDto mapToDto(Boutique boutique){

        if(boutique !=null){
            return new BoutiqueDto(
                    boutique.getId(),
                    boutique.getName(),
                    Collections.emptyList()
            );
        }
        return null;
    }

    public Set<BoutiqueDto> findAll(){
        return this.boutiqueRepository.findAll()
                .stream()
                .map(BoutiqueService::mapToDto)
                .collect(Collectors.toSet());
    }

    public BoutiqueDto create(BoutiqueDto boutiqueDto){
        return mapToDto(this.boutiqueRepository.save(
                new Boutique(
                        boutiqueDto.getId(),
                        boutiqueDto.getName()
                )
        ));
    }

    public BoutiqueDto findById(Long id){
        return this.boutiqueRepository.findById(id).map(BoutiqueService::mapToDto).orElse(null);
    }

    public BoutiqueDto findAllWithProductList(Long id){
        BoutiqueDto boutique = this.boutiqueRepository.findById(id).map(BoutiqueService::mapToDto).orElse(null);

        ResponseEntity<ProductDto[]> productDtoResponseEntity = this.restTemplate.getForEntity("http://localhost:9001/api/v1/products/boutiques/{id}", ProductDto[].class,id);

        ProductDto[] productArray = productDtoResponseEntity.getBody();

        System.out.println(productArray);

       List<ProductDto> productDtoList = Arrays.stream(productArray)
               .collect(Collectors.toList());

        boutique.setProducts(productDtoList);

        return null;
    }
}
