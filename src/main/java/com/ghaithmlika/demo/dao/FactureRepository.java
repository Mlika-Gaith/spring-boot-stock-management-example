package com.ghaithmlika.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ghaithmlika.demo.entities.Client;
import com.ghaithmlika.demo.entities.Facture;
import com.ghaithmlika.demo.entities.Produit;

public interface FactureRepository extends JpaRepository <Facture,Long>{
	List <Facture> findByClient(Client client);
	List <Facture> findByProduit(Produit produit);
	@Query("select f from Facture f where f.designation like :x")
	public Page<Facture> chercher(@Param("x")String mc, Pageable pageable);
	Facture findByDesignation (String designation);
	@Query("select SUM (f.somme) from Facture f")
	double somme();

}
