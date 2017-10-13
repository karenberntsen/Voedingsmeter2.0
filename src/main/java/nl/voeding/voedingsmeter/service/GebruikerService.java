package nl.voeding.voedingsmeter.service;
import java.util.List;

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
		gebruikerRepository.save(gebruiker);
		return gebruiker;
	}
	
	public List<Gebruiker> getAll() {
		return (List<Gebruiker>)gebruikerRepository.findAll();
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