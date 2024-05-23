package com.dat109.hvl.no.databaseTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.dat109.hvl.no.eventbooking.EventbookingApplication;
import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Passord;
import com.dat109.hvl.no.eventbooking.Repository.ArrangementRepository;
import com.dat109.hvl.no.eventbooking.Service.ArrangementService;
import com.dat109.hvl.no.eventbooking.Service.SookeService;

import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EventbookingApplication.class)
class TestArrangementService {

		@Mock
		ArrangementRepository mockedArrangementRepo;

		@InjectMocks
		ArrangementService aService; 
	
		
		Adresse adresse = new Adresse("Bergen", "Festplassen 1", "5080");
		Passord pass = new Passord("pass");
		Bruker bruker = new Bruker("Reidar", "Nilsen", "Bergen", "Veien 13", "5080", "12121212", "reinils@gmail.no", pass.getHash(), pass.getSalt());
	
		Arrangement a1 = new Arrangement(LocalDateTime.of(2024, 6, 6, 18, 30), LocalDateTime.of(2024, 6, 7, 6, 30), "Eksamensfest", "feiring.no", "fest.img", 50, AType.Dans, adresse, bruker);
		Arrangement a2 = new Arrangement(LocalDateTime.of(2024, 7, 12, 14, 0), LocalDateTime.of(2024, 7, 14, 23, 0), "Festivalen", "festivalen.no", "festival.img", 2500, AType.Festival, adresse, bruker);
		Arrangement a3 = new Arrangement(LocalDateTime.of(2024, 8, 15, 16, 0), LocalDateTime.of(2024, 8, 15, 17, 0), "Visning", "kjophus.no", "leilighet.img", 0, AType.Visning, adresse, bruker);
		
		
		@Test
		/**
		 * test om vi finner alle arrangement 
		 */
		public void finnAlleArrangement() {
			
				
			when(mockedArrangementRepo.findAll()).thenReturn(List.of( a1, a2, a3));
			
			List<Arrangement> resultat = aService.hentAlle(); 
			
			assertEquals(3, resultat.size()); 
	}
	
		
		@Test 
		/**
		 * legg til arrangement 
		 * Test legg til Arrangement metode i Arrangement service 
		 */
		public void leggTilArrangement() {
			
			a1.setId(1);
			
			mockedArrangementRepo.save(a1);
			
			when(mockedArrangementRepo.findById(1)).thenReturn(a1);

			assertEquals(a1.getAdresse(), mockedArrangementRepo.findById(a1.getId()).getAdresse());
			
		}
		
		@Test 
		/**
		 * TODO
		 * Test for å slette enkelt arrangement fra databasen 
		 */
		public void slettArrangement() {
			
			a1.setId(1);
			
			mockedArrangementRepo.save(a1);
			
			mockedArrangementRepo.delete(a1);
			
			
			when(mockedArrangementRepo.findAll()).thenReturn(List.of());

			assertEquals(0, mockedArrangementRepo.findAll().size());
			

			
		}
		
		
		
		
}