package nl.voeding.voedingsmeter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.voeding.voedingsmeter.model.Product;

@Component
public interface ProductRepository extends CrudRepository<Product, Long> {

	public List<Product> findByNaamLikeIgnoreCase(String naam);
	
}
