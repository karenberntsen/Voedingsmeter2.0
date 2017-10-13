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

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
    @Column(name = "datum", nullable = false, unique = true)
	private LocalDate datum;
	
	@NotNull
    @JoinTable(name="producten",
               joinColumns=@JoinColumn(name="logboekdag"),
               inverseJoinColumns=@JoinColumn(name="hoeveelheid"))
    @MapKeyJoinColumn(name="product")
	private HashMap<Product,Float> producten = new HashMap<>();

	public Logboekdag() {
		datum=LocalDate.now();
	}
	
	public Logboekdag(LocalDate datum) {
		this.datum = datum;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

}
