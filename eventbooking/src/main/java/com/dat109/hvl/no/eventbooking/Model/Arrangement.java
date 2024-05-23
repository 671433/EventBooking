package com.dat109.hvl.no.eventbooking.Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "arrangement", schema = "eventBooking")
public class Arrangement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTid;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime sluttTid;
	
	private String navn;
	private String url;
	private String bilde;
	private Integer pris;
	private AType atype;
	
	@ManyToMany
	@JoinTable(name = "Paameldt",
			joinColumns = @JoinColumn(name = "BrukerID"),
			inverseJoinColumns = @JoinColumn(name = "arrangementID"))
	private List<Bruker> pomeldt;
	
	@ManyToOne
	@JoinColumn(name = "addresseid")
	private Adresse adresse;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrangor")
	private Bruker arrangor;

	/**
	 * Oppretter et objekt av type Arrangement i java
	 * 
	 * @param startTid
	 * @param sluttTid
	 * @param navn
	 * @param url
	 * @param bilde
	 * @param pris
	 * @param atype
	 * @param addresse
	 * @param arrangor
	 */
	 public Arrangement() {};
	public Arrangement(LocalDateTime startTid, LocalDateTime sluttTid, String navn, String url, String bilde, Integer pris, AType atype, Adresse adresse, Bruker arrangor) {
		super();
		this.startTid = startTid;
		this.sluttTid = sluttTid;
		this.navn = navn;
		this.url = url;
		this.bilde = bilde;
		this.pris = pris;
		this.pomeldt = null;
		this.atype = atype;
		this.adresse = adresse;
		this.arrangor = arrangor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartTid() {
		return startTid;
	}
	public void setStartTid(LocalDateTime startTid) {
		this.startTid = startTid;
	}
	public LocalDateTime getSluttTid() {
		return sluttTid;
	}
	public void setSluttTid(LocalDateTime sluttTid) {
		this.sluttTid = sluttTid;
	}
	public void setPris(Integer pris) {
		this.pris = pris;
	}
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBilde() {
		return bilde;
	}

	public void setBilde(String bilde) {
		this.bilde = bilde;
	}

	public Integer getPris() {
		return pris;
	}

	public void setPris(int pris) {
		this.pris = pris;
	}

	public AType getAtype() {
		return atype;
	}

	public void setAtype(AType atype) {
		this.atype = atype;
	}

	public List<Bruker> getPomeldt() {
		return pomeldt;
	}

	public void setPomeldt(List<Bruker> pomeldt) {
		this.pomeldt = pomeldt;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Bruker getArrangor() {
		return arrangor;
	}

	public void setArrangor(Bruker arrangor) {
		this.arrangor = arrangor;
	}
	@Override
	public String toString() {
		return "Arrangement [startTid=" + startTid + ", sluttTid=" + sluttTid + ", navn=" + navn
				+ " pris=" + pris + ", atype=" + atype + " adresse="
				+ adresse + ", arrangor=" + arrangor + "]";
	}
	
	
}
