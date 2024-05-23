package com.dat109.hvl.no.eventbooking.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.AdresseRepository;
import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;

@Service
public class ArrangementService {

	private static final String ANY_LETTER_OR_DIGIT = "[a-zA-ZæøåÆØÅ0-9]";
    private static final String ANY_LETTER = "[a-zA-ZæøåÆØÅ]";
    private static final String ANY_DIGIT = "[0-9]*";
	
	@Autowired
	private ArrangementRepository arrangementRepository;

	@Autowired
	private AdresseRepository adresseRepository;

	/**
	 * Oppretter et arrangement og lagrer det på databasen
	 * 
	 * @param startTid
	 * @param sluttTid
	 * @param navn
	 * @param url
	 * @param bilde
	 * @param pris
	 * @param atype
	 * @param adresse
	 * @param poststed
	 * @param gate
	 * @param postNummer
	 * @param arrangor
	 * @return
	 */
	public boolean opprettArrangement(LocalDateTime startTid, LocalDateTime sluttTid, String navn, String url,
			String bilde, Integer pris, AType atype, Adresse adresse, String poststed, String gate, String postNummer,
			Bruker arrangor) {

		Adresse nyAdresse = new Adresse(poststed, gate, postNummer);
		Optional<Adresse> nA = findAd(nyAdresse);

		if (nA.isEmpty()) {
			adresseRepository.save(nyAdresse);
			nyAdresse = findAd(nyAdresse).get();
		} else {
			nyAdresse = nA.get();
		}

		Arrangement ny = new Arrangement(startTid, sluttTid, navn, url, bilde, pris, atype, adresse, arrangor);

		ny.setAdresse(nyAdresse);
		Optional<Arrangement> nyA = findArr(ny);

		if (nyA.isEmpty()) {
			arrangementRepository.save(ny);
		} else {
			return false;
		}

		return true;
	}

	public List<Arrangement> hentAlle() {
		return arrangementRepository.findAll();
	}

	private Optional<Adresse> findAd(Adresse nyAdresse) {

		return adresseRepository.findAll().stream()
				.filter(a -> a.getPoststed().equals(nyAdresse.getPoststed()) && a.getGate().equals(nyAdresse.getGate()))
				.findFirst();
	}

	private Optional<Arrangement> findArr(Arrangement ny) {

		return arrangementRepository.findAll().stream().filter(a -> a.getAtype().equals(ny.getAtype())
				&& a.getNavn().equals(ny.getNavn()) && a.getStartTid().equals(ny.getStartTid())).findFirst();

	}

	public Arrangement finnArrangement(Integer id) {

		return arrangementRepository.findById(id).orElseThrow();

	}
	
//public boolean isValidArrangement(LocalDateTime startTid, LocalDateTime sluttTid, String navn, Integer pris, String poststed, String gate, String postNr) {
//		
//		return (isValidDato(startTid, sluttTid) && isValidTittel(navn) && isValidPris(pris) && isValidAdresse(poststed, gate, postNr));
//	}
//	
//	private boolean isValidDato(LocalDateTime startTid, LocalDateTime sluttTid) {
//		return sluttTid.compareTo(startTid) > 0;
//	}
//	
//	private boolean isValidTittel(String navn) {
//		if (navn.matches("^" + ANY_LETTER_OR_DIGIT + "$") || navn.contains(" ") || navn.contains("-")) {
//			if (navn != null && navn.toLowerCase() != navn && navn.length() >= 2
//					&& navn.length() <= 30) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	private boolean isValidPris(Integer pris) {
//		if(pris < 0) {
//			return false;
//		}
//		return true;
//	}
//	
////	private boolean isValidType(AType type) {
////		
////		AType[] atype = AType.values();
////		for(AType aType : atype) {
////			if(type.equals(aType))
////				return true;
////		}
////		return false;
////	}
//	
//	private boolean isValidAdresse(String poststed, String gate, String postNr) {
//		return (isValidPoststed(poststed) && isValidGate(gate) && isValidPostNr(postNr));
//	}
//	
//	private boolean isValidPoststed(String poststed) {
//		if (poststed.matches("^" + ANY_LETTER + "$") || poststed.contains(" ") || poststed.contains("-")) {
//			if (poststed != null && poststed.toLowerCase() != poststed && poststed.length() >= 2
//					&& poststed.length() <= 30) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	private boolean isValidGate(String gate) {
//		if (gate.matches("^" + ANY_LETTER + "$") || gate.contains(" ") || gate.contains("-")) {
//			if (gate != null && gate.toLowerCase() != gate && gate.length() >= 2
//					&& gate.length() <= 30) {
//				return true;
//			}
//		}
//		return false;
//		
//	}
//	private boolean isValidPostNr(String postNr) {
//		if(postNr.matches("^" + ANY_DIGIT+ "$") && postNr.length() == 4) {
//			return true;
//		}
//		return false;
//	}


}
