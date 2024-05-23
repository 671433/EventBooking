package com.dat109.hvl.no.ControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpSession;

import com.dat109.hvl.no.eventbooking.EventbookingApplication;
import com.dat109.hvl.no.eventbooking.Controller.RegistrerController;
import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Passord;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;
import com.dat109.hvl.no.eventbooking.Service.PassordService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;




/**
 * Tester registreringscontrollen 
 * @author Eivind Bjørnli
 */

@SpringBootTest(classes =EventbookingApplication.class)
@AutoConfigureMockMvc
class RegistrerBrukerControllerTest {
	
	@Autowired
	MockMvc mockMvc; 

	@Mock
	HttpSession mockedSession;

	@MockBean
	BrukerService bSerivce;
	
	@InjectMocks
	RegistrerController regController;
	

	//objekt som brukes i bService.opprettBruker
	Adresse adresse = new Adresse("Bergen", "Festplassen 1", "5080");
	Passord pass = new Passord("pass");
	Bruker bruker = new Bruker("Reidar", "Nilsen", "Bergen", "Veien 13", "5080", "12121212", "reinils@gmail.no", pass.getHash(), pass.getSalt());
	
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getRegistrersideTest() throws Exception {
	
	mockMvc.perform(get("/registrer")).andExpect(status().isOk()).andExpect(view().name("registrer"));
	}
	


	@Test 
	void postRegTlfFinnes() throws Exception {
		
		
		 
		when(bSerivce.oppretteBruker(bruker.getMobil(),
				bruker.getEtternavn(), bruker.getFornavn(), 
				bruker.getHash(), bruker.getSalt(),
				bruker.getEmail(), adresse.getId(),
				adresse.getPoststed(),
				adresse.getGate(),
				adresse.getPostNummer()))
				.thenReturn(false);
		
	
		mockMvc.perform(post("/registrer")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		        .param("mobil", "123456789")
		        .param("fornavn", "John")
		        .param("etternavn", "Doe")
		        .param("hash", "value")
		        .param("salt", "value")
		        .param("email", "john.doe@example.com")
		        
		        .param("id", "1")
		        .param("poststed", "SomePoststed")
		        .param("gate", "SomeGate")
		        .param("postNummer", "1234")
		        .sessionAttr("session", mockedSession)) // Simulate HttpSession
		        .andExpect(status().is3xxRedirection())
		        .andExpect(redirectedUrl("registrer"));  	
	}
	
	
	

}
