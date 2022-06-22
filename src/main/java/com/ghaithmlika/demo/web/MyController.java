package com.ghaithmlika.demo.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ghaithmlika.demo.dao.ClientRepository;
import com.ghaithmlika.demo.dao.FactureRepository;
import com.ghaithmlika.demo.dao.ProduitRepository;
import com.ghaithmlika.demo.entities.Client;
import com.ghaithmlika.demo.entities.Facture;
import com.ghaithmlika.demo.entities.Produit;

@Controller
public class MyController {
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private FactureRepository factureRepository;
	
	// methods
	
	/** affichage **/
	@RequestMapping(value="/produits", method = RequestMethod.GET)
	public String produits(Model model,@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size,
			@RequestParam(name="motCle", defaultValue="") String motCle) {
			Page<Produit> produits = produitRepository.chercher("%"+motCle+"%",PageRequest.of(page, size));
			model.addAttribute("produits",produits.getContent());
			int [] pages = new int [produits.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",size);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",motCle);
		return"products";
	}
	
	@RequestMapping(value="/clients", method = RequestMethod.GET)
	public String clients(Model model,@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size,
			@RequestParam(name="motCle", defaultValue="") String motCle) {
			Page<Client> clients = clientRepository.chercher("%"+motCle+"%",PageRequest.of(page, size));
			model.addAttribute("clients",clients.getContent());
			int [] pages = new int [clients.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",size);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",motCle);
		return"clients";
	}
	
	@RequestMapping(value="/factures", method = RequestMethod.GET)
	public String factures(Model model,@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size,
			@RequestParam(name="motCle", defaultValue="") String motCle) {
			Page<Facture> factures = factureRepository.chercher("%"+motCle+"%",PageRequest.of(page, size));
			model.addAttribute("factures",factures.getContent());
			int [] pages = new int [factures.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",size);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",motCle);
		return"factures";
	}  
	
	/** affichage **/
	
	/** suppression **/
	
	@RequestMapping(value="/supprimer_produit" , method=RequestMethod.GET)
	public String supprimer_produit(long id, String motCle, int page, int size) {
		produitRepository.deleteById(id);
		return "redirect:/produits?page="+page+"&size="+size+"&motCle="+motCle;
	}
	@RequestMapping(value="/supprimer_client" , method=RequestMethod.GET)
	public String supprimer_client(long id, String motCle, int page, int size) {
		clientRepository.deleteById(id);
		return "redirect:/clients?page="+page+"&size="+size+"&motCle="+motCle;
	}
	@RequestMapping(value="/supprimer_facture" , method=RequestMethod.GET)
	public String supprimer_facture(long id, String motCle, int page, int size) {
		factureRepository.deleteById(id);
		return "redirect:/factures?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	/** suppression **/
	
	/** ajouter **/
	
	
	@RequestMapping(value="/nouveau_produit", method=RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("produit",new Produit());
		return "new_product.html";
	}
	@RequestMapping(value="/nouveau_client", method=RequestMethod.GET)
	public String formClient(Model model) {
		model.addAttribute("client",new Client());
		return "new_client";
	}
	@RequestMapping(value="/save_client", method=RequestMethod.POST)
	public String save_client(Model model, @Valid @ModelAttribute("client") Client client, BindingResult bindingRes) {
		if (bindingRes.hasErrors()) {
			return "new_client";
		}
		else {
			clientRepository.save(client);
			return"confirmation_client";
		}
		
	}
	@RequestMapping(value="/nouvelle_facture", method=RequestMethod.GET)
	public String formFacture(Model model) {
		model.addAttribute("factue", new Facture());
		model.addAttribute("titre","nouvelle facture");
		return "new_facture";
	}
	@RequestMapping(value="/save_facture", method=RequestMethod.POST)
	public String save_facture(Model model, @Valid @ModelAttribute("facture") Facture facture, 
			BindingResult bindingRes,@RequestParam("designation") String des,
			@RequestParam("email") String email,
			@RequestParam("produit") String produitDes) {
			Client client = clientRepository.findByEmail(email);
			Produit produit = produitRepository.findByDesignation(produitDes);
			factureRepository.save(new Facture(des,produit.getPrix(),client,produit));
			return "confirmation_facture";
			
			/*
			System.out.println(des);
			System.out.println(email);*/
			
	
	}
	@RequestMapping(value="/save_produit", method=RequestMethod.POST)
	public String save_produit(Model model, @Valid @ModelAttribute("produit") Produit produit, BindingResult bindingRes) {
		System.out.println(bindingRes.hasErrors());
		if (bindingRes.hasErrors()) {
			return "new_product";
		}
		else {
			produitRepository.save(produit);
			return "confirmation_produit";
		}
				
	}
	
	/** ajouter **/
	
	/** modifier **/
	
	
	@RequestMapping(value="/modifier_produit/{id}", method= RequestMethod.GET)
	public String modifier_produit_page(Model model, @ PathVariable Long id, @ModelAttribute("produit")Produit produit) {
		model.addAttribute("produit", produitRepository.getById(id));
		return "edit_product";
		
	}
	@PostMapping(value="/modifier_produit/{id}")
	public String modifier_produit(Model model, @PathVariable Long id, @ModelAttribute("produit")Produit produit) {
		produitRepository.updateProduit(produit.getDesignation(),produit.getPrix(), produit.getQuantite(), id);
		return "redirect:/produits";
	}
	@RequestMapping(value="/modifier_client/{id}", method= RequestMethod.GET)
	public String modifier_client_page(Model model, @PathVariable Long id, @ModelAttribute("client") Client client) {
		model.addAttribute("client",clientRepository.getById(id));
		model.addAttribute("titre","modifier client");
		return "edit_client";
		
	}
	@PostMapping(value="/modifier_client/{id}")
	public String modifier_client(Model model, @PathVariable Long id, @ModelAttribute("client")Client client) {
		clientRepository.updateClient(client.getPrenom(),client.getNom(),client.getAge(),client.getEmail(), id);
		return "redirect:/clients";
	}
	/** modifier **/
	
	
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public String home(Model model) {
		Long num_clients = clientRepository.count();
		Long num_produits = produitRepository.count();
		Long num_factures = factureRepository.count();
		double profit = factureRepository.somme();
		model.addAttribute("clients", num_clients);
		model.addAttribute("produits", num_produits);
		model.addAttribute("factures", num_factures);
		model.addAttribute("profit", profit);
		return "home";
	}
	@RequestMapping(value="/403")
	public String accessDenied() {
		return "authorize";
	}
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
}
