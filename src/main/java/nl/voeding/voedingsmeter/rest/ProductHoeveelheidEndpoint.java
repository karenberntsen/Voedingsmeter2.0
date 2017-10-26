package nl.voeding.voedingsmeter.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.voeding.voedingsmeter.JacksonConfig;
import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.model.ProductHoeveelheid;
import nl.voeding.voedingsmeter.service.GebruikerService;
import nl.voeding.voedingsmeter.service.LogboekdagService;
import nl.voeding.voedingsmeter.service.ProductHoeveelheidService;
import nl.voeding.voedingsmeter.service.ProductService;

@RestController
public class ProductHoeveelheidEndpoint {

	@Autowired
	ProductHoeveelheidService productHoeveelheidService;
	@Autowired
	GebruikerService gebruikerService;
	@Autowired
	LogboekdagService logboekdagService;
	
	@GetMapping("/getProductHoeveelheden")
	public List<ProductHoeveelheid> getProductHoeveelheden() {
		System.out.println("getProductHoeveelheden");
		return productHoeveelheidService.getAll();
	}
	
	@GetMapping("getProductHoeveelhedenFromLogboekdag/{datumString}")
	public Set<ProductHoeveelheid> getProductHoeveelhedenFromLogboekdag(@PathVariable String datumString, HttpServletRequest request) {
		System.out.println("getProductHoeveelhedenFromLogboekdag");
		Logboekdag logboekdag = getLogboekdag(request,datumString);
		return logboekdag.getProducten();
	}

	private LocalDate getLocalDateFromString(String datumString) {
		return LocalDate.parse(datumString,JacksonConfig.FORMATTER);
	}
	
	private Logboekdag getLogboekdag(HttpServletRequest request, String datumString) {
		Gebruiker gebruiker = gebruikerService.getGebruikerByCookie(request.getCookies());
		LocalDate datum = getLocalDateFromString(datumString);
		return logboekdagService.getLogboekdagByGebruikerAndDatum(gebruiker, datum);
	}
	
	@PostMapping("/ProductHoeveelheidPost/{datumString}")
	public boolean postEntiteit(@RequestBody ProductHoeveelheid productHoeveelheid,@PathVariable String datumString, HttpServletRequest request) {
		System.out.println("producthoeveelheid of: "+productHoeveelheid.getProduct().getId());
		Logboekdag logboekdag = getLogboekdag(request,datumString);
		productHoeveelheidService.save(productHoeveelheid);
		logboekdag.addProductHoeveelheid(productHoeveelheid);
		logboekdagService.save(logboekdag);
		return true;
	}
	
}
