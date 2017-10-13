package nl.voeding.voedingsmeter.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Gebruiker {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String naam;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate geboortedatum;
	
	private Float lengte;
	
	@NotNull
	@OneToMany
	private Set<Logboekdag> logboek = new HashSet<>();

	//private Set<Lichaamssamenstelling> lichaamssamenstellingen = new HashSet<>();
	
	public Gebruiker() {}
	
	public Gebruiker(String naam, LocalDate geboortedatum, Float lengte) {
		this.naam = naam;
		this.geboortedatum = geboortedatum;
		this.lengte = lengte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public Float getLengte() {
		return lengte;
	}

	public void setLengte(Float lengte) {
		this.lengte = lengte;
	}
	
	public void addLogboekdag(Logboekdag logboekdag) {
		logboek.add(logboekdag);
	}
	
	public void removeLogboekdag(Logboekdag logboekdag) {
		logboek.remove(logboekdag);
	}

}
