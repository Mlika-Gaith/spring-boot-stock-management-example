package com.ghaithmlika.demo.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class Produit implements Serializable{
	@Id @GeneratedValue
	private Long id;
	@NonNull
	@Size(min = 5, max=45)
	@Column(unique=true)
	private String designation;
	@DecimalMin("100")
	private double prix;
	@Min(1)
	private int quantite;
	@OneToMany(mappedBy ="produit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Facture> factures;
	
	// Constructors
	
	public Produit() {
		
	}

	public Produit(Long id, String designation, double prix, int quantite) {
		
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}
	
	
	public Produit(String designation, double prix, int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
	
	

}
