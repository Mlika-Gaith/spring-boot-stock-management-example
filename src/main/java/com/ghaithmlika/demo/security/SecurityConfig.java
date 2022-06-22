package com.ghaithmlika.demo.security;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ghaithmlika.demo.web.MyPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception{
		// in memory authentication
		/*auth.inMemoryAuthentication().withUser("admin").roles("USER","ADMIN").password("{noop}password");
		auth.inMemoryAuthentication().withUser("user").roles("USER").password("{noop}password");*/
		// database authentication
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login as principal,pass as credentials,active from users where login=?")
		.authoritiesByUsernameQuery("select login as principal, role as role from users_role where login=?")
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/produits","/home","/").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/nouveau_produit","/save_produit","/modifier_produit",
				"/supprimer_produit","/save_client","/nouveau_client","/save_facture","/nouvelle_facture",
				"/supprimer_facture","/supprimer_client","/save_facture","/modifier_client").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new MyPasswordEncoder();
	}

}
