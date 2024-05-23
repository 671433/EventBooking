package com.dat109.hvl.no.ControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.EventbookingApplication;
import com.dat109.hvl.no.eventbooking.Controller.LoginController;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;
import com.dat109.hvl.no.eventbooking.Service.PassordService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=EventbookingApplication.class)
@AutoConfigureMockMvc
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BrukerRepository brukerRepo;

	@Mock
	private HttpSession session;

	@MockBean
	BrukerService brukerService;

	@MockBean
	PassordService passordService;
	
	@MockBean
	LoginUtil loginutil; 

	@Autowired
	private LoginController loginController;

	    private Bruker Bruker = new Bruker();
	    private  HttpServletRequest request = mock(HttpServletRequest.class);

	    private String mobil = "123456789";
        private String passord = "testPassord";
        private Bruker bruker = new Bruker();
       
	   
	    
	  @BeforeEach 
	  void setUp() {
		  
		  bruker.setMobil(mobil);
	        bruker.setSalt("salt");
	        bruker.setHash("hashed_password");
	 
	      
	         
	        
	  }
	    
	  
		/**
		 * Tester om redirect er rett om bruker prøver å logge inn, men uten hell
		 */
		@Test
		void loginIngenBruker() {

			// Mock data

			when(brukerService.finnBruker(mobil)).thenReturn(null);

			String resultat = loginController.provAaLoggeInn(mobil, passord, request, session);
			assertEquals(resultat, "redirect:login");

			verify(session).setAttribute("beskjed_message", "Finner ingen brukere med dette telefonnummeret");

		}

	    @Test 
	    void LoginFeilPassord() {
	    when(brukerService.finnBruker(mobil)).thenReturn(bruker); 
	    when(passordService.erKorrektPassord(passord, bruker.getSalt(), bruker.getHash() )).thenReturn(false);
	  
	    String resultat = loginController.provAaLoggeInn(mobil, passord, request, session); 
	    
	    assertEquals("redirect:login", resultat); 
	    
	    verify(session).setAttribute("beskjed_message", "Ugyldig passord");
	    
	    }
	    
		@Test
		void korrektInnlogging() {

			when(brukerService.finnBruker(mobil)).thenReturn(bruker);
			when(passordService.erKorrektPassord(passord, bruker.getSalt(), bruker.getHash())).thenReturn(true);
			when(request.getSession()).thenReturn(session);
			
			
			String resultat = loginController.provAaLoggeInn(mobil, passord, request, session);
			verify(session).setAttribute(("mobil"), (mobil));
			
			assertEquals("redirect:sokeside", resultat);

		}
	}

