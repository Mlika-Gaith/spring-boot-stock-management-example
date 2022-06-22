package com.ghaithmlika.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ghaithmlika.demo.dao.ProduitRepository;
import com.ghaithmlika.demo.entities.Produit;

@SpringBootApplication
@EntityScan("com.ghaithmlika.demo.entities")
@EnableJpaRepositories("com.ghaithmlika.demo.dao")
public class CrudProduitsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CrudProduitsApplication.class, args);
		
	}

}
