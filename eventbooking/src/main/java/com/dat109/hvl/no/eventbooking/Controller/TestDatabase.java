package com.dat109.hvl.no.eventbooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;

@Controller
public class TestDatabase {

	
	// Denne klasse kun for å teste datbasse , skal slutte senere 
	
	// her er endring for å sjekke push 
	@Autowired
	private static BrukerRepository brukerRepo;
	


	
	
	public static void leggTilBruker(Bruker bruker) {
		
		if(bruker != null) {
			try {
				brukerRepo.save(bruker);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Bruker hentBruker(String mobil) {
		return brukerRepo.findByMobil(mobil);
	}
	
	public static void main(String[] args) {		

		//Bruker tamer = new Bruker("45000116", "Tamer", "Deen", "0000", "salttttt");
		//leggTilBruker(tamer);
	
		
		try {
			Bruker b1 = hentBruker("45000116");
			System.out.println(b1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	// her er endring for å sjekke push 
	public void ingenting() {
		System.out.println("test push");
	}

}
