package com.dat109.hvl.no.eventbooking.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/*
 * Kobler opp til database
 * 
 * Videre opretter attributtene til klassen.
 */

@Entity
@Table(name="adresse", schema="eventBooking")
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String poststed;
	@NotNull
	private String gate;
	@NotNull
	@Column(name = "postnummer") 
	private String postNummer;

	
	/**
	 * 
	 * Oppretter et adresse-objekt med id, dersom det skulle ønskes.
	 * 
	 * @param id
	 * @param poststed
	 * @param gate
	 * @param postNummer
	 */
//	public Adresse(Integer id, String poststed, String gate, String postNummer) {
//		this.id = id;
//		this.poststed = poststed;
//		this.gate = gate;
//		this.postnummer = postNummer;
//	}
	/**
	 * Oppretter at adresse-objekt, uten id.
	 * 
	 * @param poststed
	 * @param gate
	 * @param postNummer
	 */
	public Adresse(String poststed, String gate, String postNummer) {
		this.poststed = poststed;
		this.gate = gate;
		this.postNummer = postNummer;
	}
	
	
	
	/*
	 * Getters og setters, og til slutt en toString som ikke tar med id
	 * ettersom dette ikke er nødvendig.
	 */
	
	public Adresse() {
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPoststed() {
		return poststed;
	}
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
	public String getPostNummer() {
		return postNummer;
	}
	public void setPostNummer(String postNummer) {
		this.postNummer = postNummer;
	}

	@Override
	public String toString() {
		return "Adresse [poststed=" + poststed + ", gate=" + gate + ", postNummer=" + postNummer + "]";
	}
	
	
	

}
