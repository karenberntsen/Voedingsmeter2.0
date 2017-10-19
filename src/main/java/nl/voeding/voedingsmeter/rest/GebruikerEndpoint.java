package nl.voeding.voedingsmeter.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.LocalDateDeserializer;
import nl.voeding.voedingsmeter.model.LocalDateSerializer;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.service.GebruikerService;
import nl.voeding.voedingsmeter.service.LogboekdagService;
import nl.voeding.voedingsmeter.service.ProductService;

@RestController
public class GebruikerEndpoint {

	@Autowired
	GebruikerService gebruikerService;
	
	@Autowired
	LogboekdagService logboekdagService;

	
	@GetMapping("/createGebruiker")
	public Gebruiker createGebruiker() {
		Gebruiker gebruiker = new Gebruiker("Karen",LocalDate.of(1989, 12, 25),1.7f,"ff4nu@hotmail.com","wachtwoord");
		//Gebruiker gebruiker = new Gebruiker("Karen","25-12-1989",1.7f,"ff4nu@hotmail.com","wachtwoord");
		gebruikerService.save(gebruiker);		
		return gebruiker;
	}

	@PostMapping("/addLogboekdagToGebruiker/{id}")
	public void addLogboekdagToGebruiker(@RequestBody Logboekdag logboekdag,@PathVariable int id) {
		logboekdagService.save(logboekdag);
		gebruikerService.addLogboekdag(logboekdag, id);
	}
	
	@GetMapping("/getGebruikers")
	public List<Gebruiker> getGebruikers() {
		System.out.println("getGebruikers");
		return gebruikerService.getAll();
	}
	
	@PostMapping("/GebruikerPost")
	public boolean postGebruiker(@RequestBody Gebruiker gebruiker){
		System.out.println("gebruikerPost");
		if (!gebruikerService.containsEmail(gebruiker.getEmail())) {
			gebruikerService.save(gebruiker);
			return true;
		}
		return false;
	}
	
    @GetMapping("/getGebruikerById/{id}")
	public Gebruiker getGebruikerById(@PathVariable int id) {
		System.out.println("getGebruikerById"+id);
	    return gebruikerService.getGebruikerById(id);
	}
	
    @DeleteMapping("/delGebruikerById/{id}")
    public void delGebruikerById(@PathVariable int id) {
		System.out.println("delGebruikerById"+id);
	    gebruikerService.delGebruikerById(id);
	}

}
