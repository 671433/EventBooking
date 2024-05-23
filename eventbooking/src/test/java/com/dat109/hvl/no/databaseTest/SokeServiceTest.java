package com.dat109.hvl.no.databaseTest;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import com.dat109.hvl.no.eventbooking.EventbookingApplication;
import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Passord;
import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;
import com.dat109.hvl.no.eventbooking.Service.SookeService;

/**
 * Tester metodene sookeTestService 
 * TODO implementer 
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes= EventbookingApplication.class)
class SokeServiceTest {
	
	@Mock
	ArrangementRepository mockedArrangementRepo;

	@InjectMocks
	SookeService sService; 

	@Autowired
    private ApplicationContext applicationContext;
	

	
	/**
	 * Adreese objekt til bruk i testing 
	 */
	Adresse adresse = new Adresse("Bergen", "Festplassen 1", "5080");
	Adresse adresse2 = new Adresse("Oslo", "Karl johan", "0154"); 
	/**
	 * PassordObjekt til bruk i testing 
	 */
	Passord pass = new Passord("pass");
	/**
	 * Brukerobjekt til bruk i testing 
	 */
	Bruker bruker = new Bruker("Reidar", "Nilsen", "Bergen", "Veien 13", "5080", "12121212", "reinils@gmail.no", pass.getHash(), pass.getSalt());

	/**
	 * Arrangement objekter som skal brukes for mocking av databasen. 
	 */
	Arrangement a1 = new Arrangement(LocalDateTime.of(2024, 6, 6, 18, 30), LocalDateTime.of(2024, 6, 7, 6, 30), "Eksamensfest", "feiring.no", "fest.img", 50, AType.Dans, adresse, bruker);
	Arrangement a2 = new Arrangement(LocalDateTime.of(2024, 7, 12, 14, 0), LocalDateTime.of(2024, 7, 14, 23, 0), "Festivalen", "festivalen.no", "festival.img", 2500, AType.Festival, adresse, bruker);
	Arrangement a3 = new Arrangement(LocalDateTime.of(2024, 8, 15, 16, 0), LocalDateTime.of(2024, 8, 15, 17, 0), "Visning", "kjophus.no", "leilighet.img", 0, AType.Visning, adresse, bruker);
	Arrangement a4 = new Arrangement(LocalDateTime.of(2024, 8, 15, 16, 0), LocalDateTime.of(2024, 8, 15, 17, 0), "Visning", "kjophus.no", "leilighet.img", 0, AType.Visning, adresse2, bruker); 

	

	
	@Test
	public void contextLoads() {
        // Check if the application context is not null
        assertNotNull(applicationContext);
    }
	
	@Test 
	/**
	 * Henter arrangement ut fra sted
	 */
	public void hentArrangementAvAdresse() {
	
		
		
		when(mockedArrangementRepo.findAll()).thenReturn(List.of( a1, a2, a3,a4));
		
		List<Arrangement> arrangementListe = mockedArrangementRepo.findAll(); 
		
		List<Arrangement> resultat1 = sService.hentArrangementAvAdresse(arrangementListe, "Bergen");
		List<Arrangement> resultat2 = sService.hentArrangementAvAdresse(arrangementListe, "Oslo"); 
		
		assertEquals(3, resultat1.size()); 
		assertTrue(resultat1.get(0).getAtype().equals(a1.getAtype())); 
		assertEquals(1,resultat2.size()); 
		assertFalse(resultat2.get(0).getAdresse().getPoststed() == "Bergen"); 
	
	}
	
	@Test 
	/**
	 * Test om hentArrangementAvDato fungerer som forventet 
	 */
	public void hentArrangementAvDato() {
		when(mockedArrangementRepo.findAll()).thenReturn(List.of( a1, a2, a3,a4));
		
		List<Arrangement> arrangementListe = mockedArrangementRepo.findAll();
		
		List <Arrangement> resultat = sService.hentArrangementAvDato(arrangementListe, LocalDateTime.of(2024, 6, 6, 18, 30).toLocalDate());
		
		
		assertEquals(a1, resultat.get(0)); 
		assertEquals(0, sService.hentArrangementAvDato(arrangementListe, LocalDateTime.of(2023, 6, 6, 18, 30).toLocalDate()).size()); 
		
	}
	

	
	
}
