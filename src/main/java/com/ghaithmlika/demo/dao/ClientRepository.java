package com.ghaithmlika.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ghaithmlika.demo.entities.Client;
import com.ghaithmlika.demo.entities.Produit;

public interface ClientRepository extends JpaRepository<Client,Long>{
	Client findByEmail(String email);
	@Query("select c from Client c where c.prenom like :x")
	public Page<Client> chercher(@Param("x")String mc, Pageable pageable);
	Client findByPrenom (String prenom);
	@Transactional
	@Modifying
	@Query("update Client c set c.prenom = ?1, c.nom = ?2, c.age = ?3, c.email = ?4 where c.id = ?5")
	void updateClient(String prenom, String nom,int age,String email, Long id);

}
