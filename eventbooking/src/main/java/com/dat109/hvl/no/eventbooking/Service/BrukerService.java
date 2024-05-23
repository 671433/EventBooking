package com.dat109.hvl.no.eventbooking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Pameldt;
import com.dat109.hvl.no.eventbooking.Repository.AdresseRepository;

import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;


/*
 * 
 * Klasse for Bruker service 
 * 
 */

@Service
public class BrukerService {

	@Autowired
	private BrukerRepository brukerRepository;



	@Autowired
	private AdresseRepository adresseRepository;
	
//	@Autowired
//	private PameldtRepository pameldtRepository;

	@Autowired
	private PassordService passordService;

	/**
	 * 
	 * @param mobil
	 * @return bruker med gitt mobilnummer.
	 */

//    public Bruker finnBruker(String mobil) {
//        Optional<Bruker> d = brukerRepository.findByMobil(mobil);
//        
//        if (!(d.isPresent())) {
//            return null;
//        }
//        return d.get();
//    }

	public Bruker finnBruker(String mobil) {
		return brukerRepository.findByMobil(mobil);

	}

	
	/**
	 * Melder på en brukre til et Arrangement og oppdaterer databasen.
	 * 
	 * @param arrangement
	 * @param bruker
	 * @return false om bruker alleredet er påmeldt og true om den ble meldt på.
	 */
//	public boolean MeldPaaArrangement(Arrangement ar, Bruker b) {
//		// String retur;
//		for (Arrangement a : b.getPomeldt()) {
//
//			if (a == ar) {
//				// retur = "Du er allerede registrert";
//				return false;
//			}
//		}
//
//		Pameldt p = new Pameldt(b, ar);
//		
//		// retur = "Du er registrert";
//		
//		pameldtRepository.save(p);
//		ar.getPomeldt().add(b);
//		b.getPomeldt().add(ar);
//		return true;
//
//	}

	public boolean oppretteBruker(String mobile, String etternavn, String fornavn, String hash, String salt,
			String email, Integer adressId, String poststed, String gate, String postNummer) {

		// password implementer later
		salt = passordService.genererTilfeldigSalt();

		String hashOgSalt = passordService.hashMedSalt(hash, salt);

		if (brukerRepository.findByMobil(mobile) != null) {
			return false;
		}

		if (poststed == null || poststed == null || postNummer == null) {

			return false;
		}

		if (hash == null || salt == null) {
			return false;
		}

		Adresse nyAdresse = new Adresse(poststed, gate, postNummer);
		Optional<Adresse> nA = findAd(nyAdresse);

		if (nA.isEmpty()) {
			adresseRepository.save(nyAdresse);
			nyAdresse = findAd(nyAdresse).get();
		} else {
			nyAdresse = nA.get();
		}
		
		adresseRepository.save(nyAdresse);

		Bruker ny = new Bruker(mobile, etternavn, fornavn, hashOgSalt, salt, email, adressId);

		ny.setAdresse(nyAdresse);

		brukerRepository.save(ny);

		return true;

	}
	
	private Optional<Adresse> findAd(Adresse nyAdresse) {

		return adresseRepository.findAll().stream()
				.filter(a -> a.getPoststed().equals(nyAdresse.getPoststed()) && a.getGate().equals(nyAdresse.getGate()))
				.findFirst();
	}

}
