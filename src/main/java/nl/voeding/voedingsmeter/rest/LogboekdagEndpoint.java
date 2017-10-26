package nl.voeding.voedingsmeter.rest;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
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

import nl.voeding.voedingsmeter.JacksonConfig;
import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.model.ProductHoeveelheid;
import nl.voeding.voedingsmeter.service.GebruikerService;
import nl.voeding.voedingsmeter.service.LogboekdagService;
import nl.voeding.voedingsmeter.service.ProductService;

@RestController
public class LogboekdagEndpoint {

	@Autowired
	LogboekdagService logboekdagService;
	
	@Autowired
	GebruikerService gebruikerService;

	
	@GetMapping("/createLogboekdag")
	public Logboekdag createLogboekdag() {
		Gebruiker gebruiker = new Gebruiker("japie",LocalDate.of(1970, 8, 3),1.85f,"japie@hotmail.nl","japie");
		System.out.println(gebruiker);
		gebruikerService.save(gebruiker);
		Logboekdag logboekdag = new Logboekdag(gebruiker);
		logboekdagService.save(logboekdag);
		Logboekdag logboekdag2 = new Logboekdag(gebruiker,LocalDate.of(2017, 10, 10));
		logboekdagService.save(logboekdag2);
		return logboekdag;
	}

	@GetMapping("/getLogboek")
	public List<Logboekdag> getLogboek() {
		System.out.println("getLogboek");
		return logboekdagService.getAll();
	}
	
	@GetMapping("/getLogboekFromGebruiker")
	public List<LocalDate> getLogboekFromGebruiker(HttpServletRequest request) {
		System.out.println("getLogboek");
		Gebruiker gebruiker = gebruikerService.getGebruikerByCookie(request.getCookies());
		return gebruiker.getLogboek().stream().map(lb->lb.getDatum()).collect(Collectors.toList());
	}
	
	@PostMapping("/LogboekdagPost")
	public boolean postEntiteit(@RequestBody Logboekdag logboekdag,HttpServletRequest request) {
		System.out.println(logboekdag.getDatum().toString());
		Gebruiker gebruiker = gebruikerService.getGebruikerByCookie(request.getCookies());
		if (logboekdagService.existsByGebruikerAndDate(gebruiker, logboekdag.getDatum())) {
			return false;
		}
		logboekdag.zetGebruiker(gebruiker);
		logboekdagService.save(logboekdag);
		return true;
	}
	
	
    @GetMapping("/getLogboekdagById/{id}")
	public Logboekdag getLogboekdagById(@PathVariable int id) {
		System.out.println("getLogboekdagById"+id);
	    return logboekdagService.getLogboekdagById(id);
	}
	
    @DeleteMapping("/delLogboekdagById/{id}")
    public void delLogboekdagById(@PathVariable int id) {
		System.out.println("delLogboekdagById"+id);
	    logboekdagService.delLogboekdagById(id);
	}

}
