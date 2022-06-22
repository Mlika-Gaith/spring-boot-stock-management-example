package com.ghaithmlika.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Facture {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String designation;
	private double somme;
	@ManyToOne(fetch = FetchType.LAZY, optional= true)
	@JoinColumn(name="client_id", nullable=true)
	private Client client;
	@ManyToOne(fetch=FetchType.LAZY, optional = true)
	@JoinColumn(name="produit_id", nullable=true)
	private Produit produit;
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
	public double getSomme() {
		return somme;
	}
	public void setSomme(double somme) {
		this.somme = somme;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Facture(String designation, double somme, Client client, Produit produit) {
		super();
		this.designation = designation;
		this.somme = somme;
		this.client = client;
		this.produit = produit;
	}
	public Facture() {
		
	}
	
	
}
