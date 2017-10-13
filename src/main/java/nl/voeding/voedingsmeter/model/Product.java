package nl.voeding.voedingsmeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;

@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String naam;
	
	@NotNull
	private Float hoeveelheid;
	
	@NotNull
	private Eenheid eenheid=Eenheid.GRAM;
	
	@NotNull
	private Productgroep productgroep=Productgroep.OVERIG;
	
	@Column(name="kcal",nullable=true)	
	private Float kcal=null;
	
	@Column(name="eiwit",nullable=true)
	private Float eiwit=null;
	
	@Column(name="vet",nullable=true)
	private Float vet=null;
	
	@Column(name="verzadigdVet",nullable=true)
	private Float verzadigdVet=null;
	
	@Column(name="onverzadigdVet",nullable=true)
	private Float onverzadigdVet=null;
	
	@Column(name="koolhydraten",nullable=true)
	private Float koolhydraten=null;

	@Column(name="suikers",nullable=true)
	private Float suikers=null;
	
	@Column(name="fructose",nullable=true)
	private Float fructose=null;

	@Column(name="vezels",nullable=true)
	private Float vezels=null;
	
	@Column(name="zout",nullable=true)
	private Float zout=null;

	@NotNull
	private String bron;

	public Product() {};
	
	public Product(String naam, Float hoeveelheid, Eenheid eenheid, Productgroep productgroep, Float kcal, Float eiwit, Float vet,
			Float verzadigdVet, Float onverzadigdVet, Float koolhydraten, Float suikers, Float fructose, Float vezels,
			Float zout,String bron) {
		this.naam = naam;
		this.hoeveelheid = hoeveelheid;
		this.eenheid = eenheid;
		this.productgroep = productgroep;
		this.kcal = kcal;
		this.eiwit = eiwit;
		this.vet = vet;
		this.verzadigdVet = verzadigdVet;
		this.onverzadigdVet = onverzadigdVet;
		this.koolhydraten = koolhydraten;
		this.suikers = suikers;
		this.fructose = fructose;
		this.vezels = vezels;
		this.zout = zout;
		this.bron = bron;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setHoeveelheid(Float hoeveelheid) {
		this.hoeveelheid = hoeveelheid<=0 ? 0 : hoeveelheid;
	}

	public void setEenheid(Eenheid eenheid) {
		this.eenheid = eenheid;
	}

	public void setKcal(Float kcal) {
		this.kcal = kcal;
	}

	public void setEiwit(Float eiwit) {
		this.eiwit = eiwit;
	}

	public void setVet(Float vet) {
		this.vet = vet;
	}

	public void setVerzadigdVet(Float verzadigdVet) {
		this.verzadigdVet = verzadigdVet;
	}

	public void setOnverzadigdVet(Float onverzadigdVet) {
		this.onverzadigdVet = onverzadigdVet;
	}

	public void setKoolhydraten(Float koolhydraten) {
		this.koolhydraten = koolhydraten;
	}

	public void setSuikers(Float suikers) {
		this.suikers = suikers;
	}

	public void setFructose(Float fructose) {
		this.fructose = fructose;
	}

	public void setVezels(Float vezels) {
		this.vezels = vezels;
	}

	public void setZout(Float zout) {
		this.zout = zout;
	}

	public void setBron(String bron) {
		this.bron = bron;
	}

	public Long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Float getHoeveelheid() {
		return hoeveelheid;
	}

	public Eenheid getEenheid() {
		return eenheid;
	}

	public Float getKcal() {
		return kcal;
	}

	public Float getEiwit() {
		return eiwit;
	}

	public Float getVet() {
		return vet;
	}

	public Float getVerzadigdVet() {
		return verzadigdVet;
	}

	public Float getOnverzadigdVet() {
		return onverzadigdVet;
	}

	public Float getKoolhydraten() {
		return koolhydraten;
	}

	public Float getSuikers() {
		return suikers;
	}

	public Float getFructose() {
		return fructose;
	}

	public Float getVezels() {
		return vezels;
	}

	public Float getZout() {
		return zout;
	}

	public String getBron() {
		return bron;
	}
	
	
}