package nl.voeding.voedingsmeter.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProductHoeveelheid {

	private Long id;

	private Product product;
	
	private Float hoeveelheid;
	
	public ProductHoeveelheid() {}

	public ProductHoeveelheid(Long id, Product product, Float hoeveelheid) {
		super();
		this.id = id;
		this.product = product;
		this.hoeveelheid = hoeveelheid;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne(targetEntity=Product.class,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@NotNull
	public Float getHoeveelheid() {
		return hoeveelheid;
	}

	public void setHoeveelheid(Float hoeveelheid) {
		if (hoeveelheid>0) {
			this.hoeveelheid = hoeveelheid;
		}
	}
	
	public void add(Float hoeveelheid) {
		if (hoeveelheid>0) {
			this.hoeveelheid+=hoeveelheid;
		} else {
			throw new IllegalArgumentException("De hoeveelheid kan niet negatief zijn.");
		}
	}
	
	public void substract(Float hoeveelheid) {
		this.hoeveelheid= hoeveelheid<this.hoeveelheid ? this.hoeveelheid-hoeveelheid : 0;
	}
	
}
