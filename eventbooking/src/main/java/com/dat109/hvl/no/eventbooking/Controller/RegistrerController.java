package com.dat109.hvl.no.eventbooking.Controller;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;


@Controller
@RequestMapping("/registrer")
public class RegistrerController {
	
	@Autowired
	private BrukerService brukerService;


/*
	 * Henter registreringssiden.
	 */
	
	@GetMapping
	public String getRegistrerSide() {
		
		return "registrer";
	}
	
	/*
	 * Henter kvitteringsside for registrering 
	 */
	
	@PostMapping
	public String postRegistrerSide(@Valid @ModelAttribute("bruker")Bruker bruker, @ModelAttribute("Adresse")Adresse adresse,BindingResult bindingResult,
			Model model, HttpServletRequest request, RedirectAttributes ra, HttpSession session){


		
		boolean lageBruker = brukerService.oppretteBruker(bruker.getMobil(),bruker.getEtternavn(), bruker.getFornavn(), bruker.getHash(), bruker.getSalt(), bruker.getEmail(), adresse.getId(),
							adresse.getPoststed(), adresse.getGate(),adresse.getPostNummer());
		
		
		// hvis det ikke opperttet ny bruker
		if (!lageBruker) {
			ra.addFlashAttribute("feilmeldinger", "Kunne ikke registrere bruker, telefonnummer er allerede i bruk");
			return "redirect:registrer";
		
		}
		
		if (LoginUtil.erBrukerInnlogget(session))

		{
			ra.addFlashAttribute("feilmeldinger", "Kunne ikke registrere bruker, du er allerede logget inn som bruker");
			return "redirect:registrer";

		}
		
		
		LoginUtil.loggInnBruker(request, bruker.getMobil());
		return "redirect:sokeside";
	}
	
}
