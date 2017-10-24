package nl.voeding.voedingsmeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.voeding.voedingsmeter.enums.Eenheid;
import nl.voeding.voedingsmeter.enums.Productgroep;

@Entity
public class Product {

	private Long id;

	private String naam;
	
	private Float inhoud;
	
	private Eenheid eenheid=Eenheid.GRAM;
	
	private Productgroep productgroep=Productgroep.OVERIG;
	
	private Float kcal=null;
	
	private Float eiwit=null;
	
	private Float vet=null;
	
	private Float verzadigdVet=null;
	
	private Float onverzadigdVet=null;
	
	private Float koolhydraten=null;

	private Float suikers=null;
	
	private Float fructose=null;

	private Float vezels=null;
	
	private Float zout=null;
	
	private String bron;

	public Product() {};
	
	public Product(String naam, Float inhoud, Eenheid eenheid, Productgroep productgroep, Float kcal, Float eiwit, Float vet,
			Float verzadigdVet, Float onverzadigdVet, Float koolhydraten, Float suikers, Float fructose, Float vezels,
			Float zout,String bron) {
		this.naam = naam;
		this.inhoud = inhoud;
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

	public void setInhoud(Float inhoud) {
		this.inhoud = inhoud<=0 ? 0 : inhoud;
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
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@NotNull
	public String getNaam() {
		return naam;
	}

	@NotNull
	public Float getInhoud() {
		return inhoud;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public Eenheid getEenheid() {
		return eenheid;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public Productgroep getProductgroep() {
		return productgroep;
	}

	@Column(name="kcal",nullable=true)
	public Float getKcal() {
		return kcal;
	}

	@Column(name="eiwit",nullable=true)
	public Float getEiwit() {
		return eiwit;
	}

	@Column(name="vet",nullable=true)
	public Float getVet() {
		return vet;
	}

	@Column(name="verzadigdVet",nullable=true)
	public Float getVerzadigdVet() {
		return verzadigdVet;
	}

	@Column(name="onverzadigdVet",nullable=true)
	public Float getOnverzadigdVet() {
		return onverzadigdVet;
	}

	@Column(name="koolhydraten",nullable=true)
	public Float getKoolhydraten() {
		return koolhydraten;
	}

	@Column(name="suikers",nullable=true)
	public Float getSuikers() {
		return suikers;
	}

	@Column(name="fructose",nullable=true)
	public Float getFructose() {
		return fructose;
	}

	@Column(name="vezels",nullable=true)
	public Float getVezels() {
		return vezels;
	}

	@Column(name="zout",nullable=true)
	public Float getZout() {
		return zout;
	}

	@NotNull
	public String getBron() {
		return bron;
	}

	public void setProductgroep(Productgroep productgroep) {
		this.productgroep = productgroep;
	}
	
	
}