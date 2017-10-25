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
	
	public List<String> getCookies() {
		 return getAll().stream().map(gebruiker -> gebruiker.getCookie())
				 .filter(cookie->cookie!=null).collect(Collectors.toList());
	}
	
	public Gebruiker getGebruikerByCookie(Cookie[] cookies) {
		if (cookies != null) {
			if (cookies.length>1) {
				System.out.println("more than one cookie found");
			}
			return gebruikerRepository.findByCookie(cookies[0].getValue()).get(0);
    	} else {
    		throw new NullPointerException("No cookies found");
    	}
	}
	
	public boolean hasCookie(String cookie) {
        if (cookie!= null) {
            return getCookies().contains(cookie);
        }
		return false;
	}
	
	public List<Gebruiker> getAll() {
		return (List<Gebruiker>)gebruikerRepository.findAll();
	}
	
	public List<Gebruiker> getGebruikerByCookie(String cookie) {
		return gebruikerRepository.findByCookie(cookie);
	}
	
	public boolean bevatEmail(String mail) {
		return getAll().stream().map(i->i.getEmail()).anyMatch(email -> email.equalsIgnoreCase(mail));
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