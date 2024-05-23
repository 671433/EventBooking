package com.dat109.hvl.no.eventbooking.Model;

import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name="bruker", schema ="eventBooking")
public class Bruker {

	@Id
	private String mobil;
	
	@NotNull
	private String fornavn;
	@NotNull
	private String etternavn;

	@OneToOne
	private Adresse adresse;
	@NotNull
	private String email;

	@OneToMany
	private List<Arrangement> mineArrangement;
	
	@ManyToMany
	@JoinTable(name = "Paameldt",
			joinColumns = @JoinColumn(name = "arrangementID"),
			inverseJoinColumns = @JoinColumn(name = "brukerID"))
	private List<Arrangement> pomeldt;
	
	@NotNull
	private String hash;
	@NotNull
	private String salt;
	
//	private Passord passord;

    public Bruker() {
       
    }

	public Bruker (String mobile, String etternavn, String fornavn,String hash, String salt,String email, Integer adressId  ) {
		this.mobil = mobile;
		this.etternavn = etternavn;
		this.fornavn = fornavn;
		this.hash = hash;
		this.salt = salt;
		this.email = email;
		this.adresse = new Adresse();
        this.adresse.setId(adressId);
	}

	public Bruker(
			String fornavn,
			String etternavn,
			String poststed, String gate, String postnummer,
			String mobil,
			String email,
			String hash,
			String salt) {

		super();

		this.adresse = new Adresse(poststed, gate, postnummer);
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.email=email;
		this.hash = hash;
		this.salt = salt;

		this.mineArrangement = new ArrayList<Arrangement>();
	}



	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<Arrangement> getMineArrangement() {
		return mineArrangement;
	}

	public void setMineArrangement(List<Arrangement> mineArrangement) {
		this.mineArrangement = mineArrangement;
	}

	public List<Arrangement> getPomeldt() {
		return pomeldt;
	}

	public void setPomeldt(List<Arrangement> pomeldt) {
		this.pomeldt = pomeldt;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Bruker{" +
				"fornavn='" + fornavn + '\'' +
				", etternavn='" + etternavn + '\'' +
				", adresse=" + adresse +
				", mobil='" + mobil + '\'' +
				", email='" + email + '\'' +
				", mineArrangement=" + mineArrangement +
				'}';
	}

//	public Passord getPassord() {
//		return passord;
//	}
//
//	public void setPassord(Passord passord) {
//		this.passord = passord;
//	}


}
