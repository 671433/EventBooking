package com.dat109.hvl.no.eventbooking.Model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "paameldt", schema = "event_booking")
public class Pameldt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int pid;

    @ManyToOne
    @JoinColumn(name = "bruker_id")
    private Bruker bruker;

    @ManyToOne
    @JoinColumn(name = "arrangement_id")
    private Arrangement arrangement;

 
    public int getId() {
		return pid;
	}

    public Pameldt() {
    	
    };

	public Pameldt(Bruker bruker, Arrangement arrangement) {
		
		this.bruker = bruker;
		this.arrangement = arrangement;
		
	}

//

	public Bruker getBruker() {
		return bruker;
	}


	public void setBruker(Bruker brukerID) {
		this.bruker = brukerID;
	}


	public Arrangement getArrangement() {
		return arrangement;
	}
	

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

   
}