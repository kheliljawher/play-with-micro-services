package io.jawhar.boutiqueservice.controller;

import io.jawhar.boutiqueservice.service.BoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/boutiques")
public class BoutiqueControler {

    private final BoutiqueService boutiqueService;

    @Autowired
    public BoutiqueControler(BoutiqueService boutiqueService) {
        this.boutiqueService = boutiqueService;
    }

    @GetMapping
    public Set<BoutiqueDto> findAll(){
        return this.boutiqueService.findAll();
    }

    @PostMapping
    public BoutiqueDto create(@RequestBody BoutiqueDto boutiqueDto){
        return  this.boutiqueService.create(boutiqueDto);
    }

    @GetMapping(value = "/{id}")
    public BoutiqueDto findById(@PathVariable Long id){
        return this.boutiqueService.findById(id);
    }
    @GetMapping("/products/{id}")
    public BoutiqueDto findByProducts(@PathVariable Long id){
        return this.boutiqueService.findAllWithProductList(id);
    }
}
