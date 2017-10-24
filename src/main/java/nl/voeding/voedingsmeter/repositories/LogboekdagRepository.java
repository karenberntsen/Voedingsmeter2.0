package nl.voeding.voedingsmeter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Logboekdag;
import nl.voeding.voedingsmeter.model.Product;

@Component
public interface LogboekdagRepository extends CrudRepository<Logboekdag, Long> {

	public List<Logboekdag> findByGebruiker(Gebruiker gebruiker);
	
}
