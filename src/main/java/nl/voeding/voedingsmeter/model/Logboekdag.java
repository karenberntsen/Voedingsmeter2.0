package nl.voeding.voedingsmeter.model;

import nl.voeding.voedingsmeter.model.Gebruiker;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Logboekdag {

	private Long id;

	private LocalDate datum;
	
    private Gebruiker gebruiker;
    
	@NotNull
    @JoinTable(name="producten",
               joinColumns=@JoinColumn(name="logboekdag"),
               inverseJoinColumns=@JoinColumn(name="hoeveelheid"))
    @MapKeyJoinColumn(name="product")
	private HashMap<Product,Float> producten = new HashMap<>();

	public Logboekdag(Gebruiker gebruiker) {
		this(gebruiker,LocalDate.now());
	}
	
	public HashMap<Product, Float> getProducten() {
		return producten;
	}

	public void setProducten(HashMap<Product, Float> producten) {
		this.producten = producten;
	}

	public Logboekdag() {};
	
	public Logboekdag(Gebruiker gebruiker,LocalDate datum) {
		this.datum = datum;
		setGebruiker(gebruiker);
	}

	public void addProduct(Product product, Float hoeveelheid) {
		producten.merge(product, hoeveelheid, Float::sum);
	}
	
	public void removeProduct(Product product) {
		producten.remove(product);
	}
	
	public void removeProduct(Product product,Float hoeveelheid) {
		producten.merge(product, -hoeveelheid, Float::sum);
		if (producten.get(product)<=0) {
			producten.remove(product);
		}
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
    @ManyToOne
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		if (!gebruiker.containsLogboekdagFromDate(datum)) {
			this.gebruiker = gebruiker;
		} else {
			throw new IllegalArgumentException("Gebruiker heeft al een logboekdag met deze datum"+datum.toString());	
		}
	}
	
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

}
