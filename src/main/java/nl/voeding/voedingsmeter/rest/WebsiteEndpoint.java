package nl.voeding.voedingsmeter.rest;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Product;

@RestController
public class WebsiteEndpoint {

	@GetMapping("/getEenheden")
	public String[] getEenheden() {
		System.out.println("getEenheden");
		Eenheid[] eenheden=Eenheid.values();
		return Arrays.stream(eenheden).map(p->p.toString()).toArray(String[]::new);
	}

	@GetMapping("/getProductgroep")
	public String[] getProductgroepen() {
		System.out.println("getProductgroepen");
		Productgroep[] productgroep=Productgroep.values();
		return Arrays.stream(productgroep).map(p->p.toString()).toArray(String[]::new);
	}
	
	@GetMapping("getProductFields")
	public Object[] getProductFields() {
		return Arrays.stream(Product.class.getDeclaredFields())
				.map(i-> i.getName()).toArray();	
	}

	
}
