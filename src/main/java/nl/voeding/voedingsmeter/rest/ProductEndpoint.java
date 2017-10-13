package nl.voeding.voedingsmeter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;
import nl.voeding.voedingsmeter.model.Product;
import nl.voeding.voedingsmeter.service.ProductService;

@RestController
public class ProductEndpoint {

	@Autowired
	ProductService productService;
	
	@GetMapping("/hallo")
	public String getHallo() {
		return "Hallo allemaal";
	}
	
	@GetMapping("/createProduct")
	public Product createProduct() {
		Product appel = new Product("appel",100f,Eenheid.GRAM,Productgroep.FRUIT,60f,0f,0.2f,0f,0.2f,13f,10.4f,null,2.0f,0.003f,"http://www.voedingscentrum.nl/encyclopedie/appel.aspx");
		productService.save(appel);
		return appel;
	}

	@GetMapping("/getProducts")
	public List<Product> getProducts() {
		System.out.println("getProducts");
		return productService.getAll();
	}
	
	@PostMapping("/ProductPost")
	public String postEntiteit(@RequestBody Product product) {
		System.out.println("Jojo");
		System.out.println(product.getNaam());
		productService.save(product);
		return "happy";
	}
	
    @GetMapping("/getProductById/{id}")
	public Product getProductById(@PathVariable int id) {
		System.out.println("getProductById"+id);
	    return productService.getProductById(id);
	}
	
    @DeleteMapping("/delProductById/{id}")
    public void delProductById(@PathVariable int id) {
		System.out.println("delProductById"+id);
	    productService.delProductById(id);
	}

}
