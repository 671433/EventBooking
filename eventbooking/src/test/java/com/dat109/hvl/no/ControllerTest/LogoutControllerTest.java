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

@SpringBootTest(classes=EventbookingApplication.class)
@AutoConfigureMockMvc
class LogoutControllerTest {
	
	
	@Autowired
	MockMvc mockMvc; 

	@Test
	void testPostLogUt() throws Exception {
		HttpSession mockedSession = mock(HttpSession.class); 
		mockMvc.perform(post("/logout").contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.sessionAttr("session",mockedSession))
		.andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(""));  
		

		
	}

}
