package nl.voeding.voedingsmeter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.model.ProductHoeveelheid;

@Component
public interface ProductHoeveelheidRepository extends CrudRepository<ProductHoeveelheid, Long> {
	
}
