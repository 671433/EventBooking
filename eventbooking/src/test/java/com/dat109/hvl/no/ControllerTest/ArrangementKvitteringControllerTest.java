package com.dat109.hvl.no.ControllerTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.dat109.hvl.no.eventbooking.EventbookingApplication;





@SpringBootTest(classes =EventbookingApplication.class)
@AutoConfigureMockMvc
class ArrangementKvitteringControllerTest {

	  @Autowired
	    private MockMvc mockMvc;

	  /**
	   * Test for get request for side som viser arrangement kvittering 
	   * @throws Exception h
	   * @author Eivind B 
	   */
	@Test
	void testGetRegKvittering() throws Exception {
	
		mockMvc.perform(MockMvcRequestBuilders.get("/arrangementKvitteringside"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("arrangementKvitteringside")); 
		
		
	}

}
