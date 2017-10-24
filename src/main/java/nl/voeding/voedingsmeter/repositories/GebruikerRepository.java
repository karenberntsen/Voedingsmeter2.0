package nl.voeding.voedingsmeter.repositories;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.voeding.voedingsmeter.model.Gebruiker;
import nl.voeding.voedingsmeter.model.Product;

@Component
public interface GebruikerRepository extends CrudRepository<Gebruiker, Long> {
	
	public List<Gebruiker> findByCookie(Cookie cookie);

}
