package nl.voeding.voedingsmeter.service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.ProductHoeveelheid;
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
		return !result.isEmpty();
	}
	
	public Logboekdag getLogboekdagByGebruikerAndDatum(Gebruiker gebruiker, LocalDate datum) {
		List<Logboekdag> logboekdagen = logboekdagRepository.findByGebruikerAndDatum(gebruiker, datum);
		if (logboekdagen.isEmpty()) {
			throw new NullPointerException("logboekdag is leeg");
		}
		if (logboekdagen.size() >1) {
			System.out.println("Waarschuwing: meerdere logboeken van dezelfde gebruiker met dezelfde datum gevonden!");
		}
		return logboekdagen.get(0);
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

	public void delProductHoeveelheidFromLogboek(int id) {
		System.out.println("delphfromlogboek "+id);
		List<Logboekdag> logboeken = getAll();
		for (Logboekdag lbd:logboeken) {
			for(Iterator<ProductHoeveelheid> it = lbd.getProducten().iterator(); it.hasNext();){
				ProductHoeveelheid ph = it.next();
			    if (ph.getId().equals(new Long(id))){
			        it.remove();
			    }
			}
			save(lbd);
		}
	}
	
}