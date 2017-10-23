package nl.voeding.voedingsmeter.rest;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.service.GebruikerService;
import nl.voeding.voedingsmeter.service.LogboekdagService;

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
	
	
	@RequestMapping("/gebruikerLogin")
	public boolean gebruikerLogin(@RequestBody Gebruiker gebruiker,HttpServletRequest request,
            HttpServletResponse response) {
		Gebruiker gebruikerUitDatabase = gebruikerService.getGebruiker(gebruiker);
		if (gebruikerUitDatabase != null) {
			gebruiker.setCookie(cookieGenerator());
			return true;
		}
		System.out.println("gebruiker is null");
		return false;
	}
	
	@RequestMapping("/navBarLogboekToegang")
	public boolean navBarLogboekToegangGebruiker(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
            		     .anyMatch(cookie ->gebruikerService.hasCookie(cookie.getValue()));
        }
        return false;
	}
	
	private Cookie cookieGenerator() {
	    SecureRandom random = new SecureRandom();
	    int number =random.nextInt();
	    Cookie newCookie = new Cookie("cookie",number+"");
  		newCookie.setMaxAge(24 * 60 * 60);
  		return newCookie;	      
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
