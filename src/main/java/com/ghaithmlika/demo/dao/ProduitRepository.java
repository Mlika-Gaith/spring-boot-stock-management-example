package com.ghaithmlika.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ghaithmlika.demo.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
	@Query("select p from Produit p where p.designation like :x")
	public Page<Produit> chercher(@Param("x")String mc, Pageable pageable);
	Produit findByDesignation (String designation);
	@Transactional
	@Modifying
	@Query("update Produit p set p.designation = ?1, p.prix = ?2, p.quantite = ?3 where p.id = ?4")
	void updateProduit(String designation, double prix, int quatite, Long id);

}
