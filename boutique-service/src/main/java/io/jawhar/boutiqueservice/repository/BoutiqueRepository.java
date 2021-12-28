package io.jawhar.boutiqueservice.repository;

import io.jawhar.boutiqueservice.entity.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {

}
