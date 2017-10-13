/*package nl.voeding.voedingsmeter.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.voeding.voedingsmeter.enums.Eenheid;

@Entity
public class Recept {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String naam;
	
	@NotNull
	private Float hoeveelheid;
	
	@NotNull
	private Eenheid eenheid;
		
	@NotNull
	private HashMap<Product,Float> ingredienten = new HashMap<>();
	
	@NotNull
	private Product product;
	
	public void addProduct(Product product,Float hoeveelheid) {
		ingredienten.put(product,hoeveelheid);
		updateRecept();
	}
	
	private void updateRecept() {
		//zoek lambda
	}
	
}
*/