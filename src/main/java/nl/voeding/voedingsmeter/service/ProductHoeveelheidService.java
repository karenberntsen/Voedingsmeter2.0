package nl.voeding.voedingsmeter.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.model.ProductHoeveelheid;
import nl.voeding.voedingsmeter.repositories.ProductHoeveelheidRepository;
import nl.voeding.voedingsmeter.repositories.ProductRepository;

@Service
@Transactional
public class ProductHoeveelheidService {

	@Autowired
	ProductHoeveelheidRepository productHoeveelheidRepository;
	
	public ProductHoeveelheid save(ProductHoeveelheid productHoeveelheid) {
		productHoeveelheidRepository.save(productHoeveelheid);
		return productHoeveelheid;
	}
	
	public List<ProductHoeveelheid> getAll() {
		 return (List<ProductHoeveelheid>) productHoeveelheidRepository.findAll();
	}
	
	public ProductHoeveelheid getProductHoeveelheidById(int id) {
		System.out.println("Service:getProductHoeveelheidById"+id);
	    return productHoeveelheidRepository.findOne((long)id);
	}
	
	public void delProductHoeveelheidById(int id) {
		System.out.println("Service:delProductHoeveelheidById"+id);		
		productHoeveelheidRepository.delete((long)id);
	}
	
}