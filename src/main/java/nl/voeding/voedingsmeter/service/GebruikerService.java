package nl.voeding.voedingsmeter.service;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.repositories.GebruikerRepository;

@Service
@Transactional
public class GebruikerService {

	@Autowired
	GebruikerRepository gebruikerRepository;
	
	public Gebruiker save(Gebruiker gebruiker) {
		System.out.println("save gebruiker");
		gebruikerRepository.save(gebruiker);
		return gebruiker;
	}
	
	public Gebruiker getGebruiker(Gebruiker gebruiker) {
		Gebruiker gebruikerUitDatabase;
		Iterator itr = getAll().iterator();
		while (itr.hasNext()) {
			gebruikerUitDatabase = (Gebruiker) itr.next();
			if (gebruikerUitDatabase.equals(gebruiker)) {
				if (gebruikerUitDatabase.getWachtwoord().equals(gebruiker.getWachtwoord())) {
					return gebruikerUitDatabase;
				}
			}
		}
		return null;
	}
	
	public List<String> getCookieStrings() {
		 return getAll().stream().map(gebruiker -> gebruiker.getCookie())
				 .filter(cookie->cookie!=null).map(cookie->cookie.getValue()).collect(Collectors.toList());
	}
	
	public boolean hasCookie(String cookieString) {
		List<String> cookieStrings = getCookieStrings();
		return getCookieStrings().contains(cookieString);
	}
	
	public List<Gebruiker> getAll() {
		return (List<Gebruiker>)gebruikerRepository.findAll();
	}
	
	public boolean containsEmail(String mail) {
		return getAll().stream().map(i->i.getEmail())
		.anyMatch(email -> email.equalsIgnoreCase(email));
	}
	
	public void addLogboekdag(Logboekdag logboekdag,int id) {
		Gebruiker gebruiker = getGebruikerById(id);
		gebruiker.addLogboekdag(logboekdag);
		System.out.println(gebruiker.toString()+logboekdag.toString());
		save(gebruiker);
	}
	
	public Gebruiker getGebruikerById(int id) {
		System.out.println("Service:getProductById"+id);
	    return gebruikerRepository.findOne((long)id);
	}
	
	public void delGebruikerById(int id) {
		System.out.println("Service:delGebruikerById"+id);		
		gebruikerRepository.delete((long)id);
	}
	
}