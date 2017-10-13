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

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.service.GebruikerService;
import nl.voeding.voedingsmeter.service.LogboekdagService;
import nl.voeding.voedingsmeter.service.ProductService;

@RestController
public class LogboekdagEndpoint {

	@Autowired
	LogboekdagService logboekdagService;
	
	@GetMapping("/createLogboekdag")
	public Logboekdag createLogboekdag() {
		Logboekdag logboekdag = new Logboekdag();
		logboekdagService.save(logboekdag);
		return logboekdag;
	}

	@GetMapping("/getLogboek")
	public List<Logboekdag> getLogboek() {
		System.out.println("getLogboek");
		return logboekdagService.getAll();
	}
	
	@PostMapping("/LogboekdagPost")
	public String postEntiteit(@RequestBody Logboekdag logboekdag) {
		System.out.println("Jojo");
		System.out.println(logboekdag.getDatum().toString());
		logboekdagService.save(logboekdag);
		return "happy";
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
