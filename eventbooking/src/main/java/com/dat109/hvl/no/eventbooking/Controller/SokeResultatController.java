package com.dat109.hvl.no.eventbooking.Controller;

import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Service.PaameldtService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;
/**
 * Controllerklasse for å håndtere søk på ulike arrangementer 
 */
@Controller
@RequestMapping("sokeResultat")
public class SokeResultatController {

	@Autowired
	PaameldtService paService;
	
	/**
	 * 
	 * @return returnerer jsp fil ved navn sokeResultat.jsp
	 */
	@GetMapping
	public String getResultatSide(HttpSession session) {
		if(LoginUtil.erBrukerInnlogget(session)) {
			String mobil = (String) session.getAttribute("mobil");
			session.setAttribute("mobil", mobil);
		}
		return "sokeResultat"; 
	}

	@PostMapping
	public String meldPaArrangement(HttpSession session,
			@RequestParam(name= "arrId") String arrId) {
		
		if(LoginUtil.erBrukerInnlogget(session)) {
			
				Integer id = Integer.parseInt(arrId);
				String mobil = (String) session.getAttribute("mobil");
				
				paService.meldPa(mobil, id);
				
				session.setAttribute("meldtPa", "Du har blitt meldt på");
		}
		
	
		
		return"redirect:sokeResultat";
	}

	
}
