package nl.voeding.voedingsmeter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.voeding.voedingsmeter.model.Product;

@Component
public interface ProductRepository extends CrudRepository<Product, Long> {

}
