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
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class Client implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	@Size(min = 3, max=45)
	private String prenom;
	@NonNull
	@Size(min = 5, max=45)
	private String nom;
	@NonNull
	@Size(min = 5, max=45)
	@Column(unique=true)
	private String email;
	@Min(18)
	private int age;
	@OneToMany(mappedBy="client", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	private Set<Facture> factures;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Client() {
		
	}
	public Client(String firstName, String lastName, String email, int age) {
		super();
		this.prenom = firstName;
		this.nom = lastName;
		this.email = email;
		this.age = age;
	}
	

}
