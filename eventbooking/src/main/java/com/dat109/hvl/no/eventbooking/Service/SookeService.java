package com.dat109.hvl.no.eventbooking.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat109.hvl.no.eventbooking.Model.*;

import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;

/**
 * Service for søkeside
 */

@Service
public class SookeService {

	@Autowired
	private ArrangementRepository as;

	/**
	 * @return Alle arrangement lagret i databasen
	 */
	public List<Arrangement> hentAlle() {
		return as.findAll();
	}

	/**
	 * Henter alle Arrangement som er av den typen man ønsker.
	 * 
	 * @param inn  referer til listen som skal søkes i
	 * @param Type arrangement man ønsker å søke på
	 * @return Liste<Arrangement> med alle arrangement som passer til denne typen
	 */
	public List<Arrangement> hentArrangementAvType(List<Arrangement> inn, String t) {
		List<Arrangement> liste = new ArrayList<Arrangement>();

		inn.stream().filter(a -> a.getAtype().toString().equals(t)).forEach(a -> liste.add(a));
		return liste;
	}

	/**
	 * Henter alle Arrangementer som er på denne adressen.
	 * 
	 * @param inn     referer til listen som skal søkes i
	 * @param Adresse
	 * @return Liste<Arrangement> med alle Arrangement som er på denne adressen.
	 */
	public List<Arrangement> hentArrangementAvAdresse(List<Arrangement> inn, String poststed) {
		List<Arrangement> liste = new ArrayList<Arrangement>();

		inn.stream().filter(a -> a.getAdresse().getPoststed().equals(poststed)).forEach(a -> liste.add(a));
		return liste;
	}

//	/**
//	 * Henter alle Arrengementer som har en varighet innefor Local time t og
//	 * feilmargin feilmarg
//	 * 
//	 * @param inn        referer til listen som skal søkes i
//	 * @param tid
//	 * @param feilmargin
//	 * @return List<Arrangement> med alle Arrangement som er av denne varigheten
//	 */
//	public List<Arrangement> hentArrangementAvVarighet(List<Arrangement> inn, LocalTime t, int feilmarg) {
//		List<Arrangement> liste = new ArrayList<Arrangement>();
//
//		int min = t.toSecondOfDay() - (t.toSecondOfDay() * feilmarg / 100);
//		int max = t.toSecondOfDay() + (t.toSecondOfDay() * feilmarg / 100);
//		inn.stream().filter(a -> a.getVarighet().compareTo(LocalTime.ofSecondOfDay(min)) > 0
//				&& a.getVarighet().compareTo(LocalTime.ofSecondOfDay(max)) < 0).forEach(a -> liste.add(a));
//
//		return liste;
//	}

	public List<Arrangement> hentArrangementAvDato(List<Arrangement> inn, LocalDate dato) {
		List<Arrangement> liste = new ArrayList<Arrangement>();

				inn.stream().filter(a -> (a.getStartTid().getMonthValue() == dato.getMonthValue()) 
								&& ( a.getStartTid().getDayOfMonth() == dato.getDayOfMonth()) 
								&& ( a.getStartTid().getYear() == dato.getYear()))
						.forEach(a -> liste.add(a));
				 		return liste;
				 
				 	}

	/**
	 * Søker på Arrengament ved hjelp av type, adresse og varighet. Om en kunde
	 * ønsker å bruke mindre enn alle filterene, blir ikke filteret brukt om det
	 * ikke er utfyllt.
	 * 
	 * @param type
	 * @param adresse
	 * @param Dato
	 * @return List<Arrangemint> med alle Arrangement som tilfredstiller de kravene
	 *         som er satt
	 */
	public List<Arrangement> sook(String t, String adresse, String dato) {
		List<Arrangement> liste = hentAlle();

		final LocalDate nyDato;
		if (dato.length() > 1) {
			nyDato = LocalDate.parse(dato);
		} else {
			nyDato = null;
		}

		if (t.length() > 1) {
			liste = hentArrangementAvType(liste, t);
		}
		if (adresse.length() > 1) {
			liste = hentArrangementAvAdresse(liste, adresse);
		}
		if (nyDato != null) {
			liste = hentArrangementAvDato(liste, nyDato);
		}
		return liste;
	}
}
