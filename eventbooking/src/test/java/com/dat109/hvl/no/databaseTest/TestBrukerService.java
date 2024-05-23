package com.dat109.hvl.no.databaseTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.dat109.hvl.no.eventbooking.EventbookingApplication;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Passord;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;

@ExtendWith(MockitoExtension.class) 
@SpringBootTest(classes= EventbookingApplication.class) 

class TestBrukerService {
	
	@Mock
	BrukerRepository mockedBrukerRep;
	
	
	@InjectMocks
	BrukerService mockedBrukerServer;

	Passord pass = new Passord("pass");
	
	@Test
	/**
	 * Test for å finne en bruker med gitt mobilnr
	 */
	void testFindByMobile() {

        String mobile = "000111";
        Bruker forventBruker = new Bruker();
        forventBruker.setFornavn("MO");
        forventBruker.setEtternavn("Salah");
        
        when(mockedBrukerRep.findByMobil(mobile)).thenReturn(forventBruker);
        
        assertEquals(forventBruker.getFornavn(), mockedBrukerServer.finnBruker(mobile).getFornavn());
        assertEquals(forventBruker.getEtternavn(), mockedBrukerServer.finnBruker(mobile).getEtternavn());
	}
	
	
	@Test 
	/**
	 * Test på å opprette en bruker som blir lagret i databasen
	 */
	void opprettBruker() {
		
		Bruker bruker = new Bruker("Reidar", "Nilsen", "Bergen", "Veien 13", "5080", "12121212", "reinils@gmail.no", pass.getHash(), pass.getSalt());

		mockedBrukerRep.save(bruker); 
		
		when(mockedBrukerRep.findByMobil("12121212")).thenReturn(bruker); 
		
		assertEquals(bruker.getMobil(), mockedBrukerServer.finnBruker(bruker.getMobil()).getMobil()); 
	
	}
}
