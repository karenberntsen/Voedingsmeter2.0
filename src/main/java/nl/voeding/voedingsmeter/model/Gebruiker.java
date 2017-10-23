package nl.voeding.voedingsmeter.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Gebruiker {
	
	private Long id;

	private String gebruikersNaam;
	
	private LocalDate geboortedatum;
	
	private Float lengte;
	
	private Set<Logboekdag> logboek = new HashSet<>();

	private String email;

	private String wachtwoord;
	
	private Cookie cookie;
	
	//private Set<Lichaamssamenstelling> lichaamssamenstellingen = new HashSet<>();
	
	public Gebruiker() {}

	public Gebruiker(String naam, LocalDate geboortedatum, Float lengte, String email,
			String wachtwoord) {
		super();
		this.gebruikersNaam = naam;
		this.geboortedatum = geboortedatum;
		this.lengte = lengte;
		this.email = email;
		this.wachtwoord = wachtwoord;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public Cookie getCookie() {
		return cookie;
	}
	
	public void setCookie(Cookie cookie) {
		this.cookie=cookie;
	}
	
	public void setGebruikersNaam(String naam) {
		this.gebruikersNaam = naam;
	}

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	private void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	@Transient
	public void setGeboortedatumAsString(String s) {
		setGeboortedatum(LocalDate.parse(s,DateTimeFormatter.ofPattern("dd-MM-yyyy")));
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
	
	@NotNull
	@OneToMany
	@JsonIgnore
	public Set<Logboekdag> getLogboek() {
		return logboek;
	}
	
	
	
	public void setLogboek(Set<Logboekdag> logboek) {
		this.logboek = logboek;
	}

	public boolean containsLogboekdagFromDate(LocalDate date) {
		//System.out.println("checking wether user "+naam+" contains logboekdag for date "+date.toString());
		return logboek.isEmpty() ? false : logboek.stream()
				                                  .map(i->i.getDatum())
				                                  .anyMatch(datum -> datum.equals(date));
	}

	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Gebruiker) {
		   Gebruiker andereGebruiker = (Gebruiker) obj;
		   if (this.id.equals(andereGebruiker.getId())) {
			   return true;
		   } else if (this.email.equalsIgnoreCase(andereGebruiker.getEmail())) {
			   return true;
		   }
		}
		return false;
	}

	@Override
	public String toString() {
		return gebruikersNaam + " is geboren op " + geboortedatum.toString() + ", is " +lengte +"m lang en is bereikbaar via: "+email;
	}

	@NotNull
	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

}
