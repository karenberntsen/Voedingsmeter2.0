package nl.voeding.voedingsmeter.service;
import java.time.LocalDate;
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
import nl.voeding.voedingsmeter.repositories.LogboekdagRepository;

@Service
@Transactional
public class LogboekdagService {

	@Autowired
	LogboekdagRepository logboekdagRepository;
	
	public Logboekdag save(Logboekdag logboekdag) {
		logboekdagRepository.save(logboekdag);
		return logboekdag;
	}
	
	public List<Logboekdag> getAll() {
		return (List<Logboekdag>)logboekdagRepository.findAll();
	}
	
	public boolean existsByGebruikerAndDate(Gebruiker gebruiker,LocalDate datum) {
		System.out.println("existsByGebruikerAndDate: datum="+datum.toString()+", gebruiker="+gebruiker.getGebruikersNaam());
		List<Logboekdag> result = logboekdagRepository.findByGebruikerAndDatum(gebruiker, datum);
		System.out.println("result is null?: "+result==null);
		System.out.println(result.toString());
		System.out.println(result.isEmpty());
		return !result.isEmpty();
	}
	
	public Logboekdag getLogboekdagById(int id) {
		System.out.println("Service:getLogboekdagById"+id);
	    return logboekdagRepository.findOne((long)id);
	}
	
	public void delLogboekdagById(int id) {
		System.out.println("Service:delLogboekdagById"+id);		
		logboekdagRepository.delete((long)id);
	}
	
	public List<Logboekdag> getLogboekByUser(Gebruiker gebruiker) {
		return logboekdagRepository.findByGebruiker(gebruiker);
	}
	
}