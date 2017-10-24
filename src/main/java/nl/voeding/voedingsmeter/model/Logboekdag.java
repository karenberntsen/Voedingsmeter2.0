package nl.voeding.voedingsmeter.model;

import nl.voeding.voedingsmeter.model.Gebruiker;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Logboekdag {

	private Long id;

	private LocalDate datum;
	
	@ManyToOne
    private Gebruiker gebruiker;
    
	private Set<ProductHoeveelheid> producten = new HashSet<>();
	
	public Logboekdag(Gebruiker gebruiker) {
		this(gebruiker,LocalDate.now());
	}

	public Logboekdag() {};
	
	public Logboekdag(Gebruiker gebruiker,LocalDate datum) {
		this.datum = datum;
		setGebruiker(gebruiker);
	}
	
	public boolean removeProduct(Product product) {
		for (ProductHoeveelheid producthoeveelheid:producten) {
			if (producthoeveelheid.getProduct().equals(product)) {
				return producten.remove(producthoeveelheid);
			}
		}
		return false;
	}//todo also remove producthoeveelheid from database; maybe not do this method in this class, but move it to the service
	
	@NotNull
	@OneToMany
	@JsonIgnore
	public Set<ProductHoeveelheid> getProducten() {
		return producten;
	}

	public void setProducten(Set<ProductHoeveelheid> producten) {
		this.producten = producten;
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
	
	@NotNull
	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

}
