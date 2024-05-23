package com.dat109.hvl.no.eventbooking.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.ArrangementService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;

@Controller
@RequestMapping("/arrangement")
public class ArrangementController {

	@Autowired
	private ArrangementService arrangementService;

	@Autowired
	private BrukerRepository brukerRepository;

	/*
	 * Henter arrangement side for oppretting av arrangement
	 */

	@GetMapping
	public String visOpprettArrangement() {

		return "arrangement";
	}

	/*
	 * Henter Kvitteringside hvis arrangement blir opprettet Returnerer til
	 * arrangement side hvis arrangementt allerede finnes
	 * 
	 */
	@PostMapping
	public String postOpprettArrangement(@Valid @ModelAttribute("arrangement") Arrangement arrangement,
			BindingResult arrangementResult, @ModelAttribute("Adresse") Adresse adresse, BindingResult adresseResult,
			Model model, HttpServletRequest request, HttpSession session) {
		
		if(arrangement.getPris() == null) {
			arrangement.setPris(0);
		}
		
		if (!LoginUtil.erBrukerInnlogget(session)) {

			session.setAttribute("feilmelding", "Du må logge på for å opprette arrangement");

			return "arrangement";

		} else {
			
//			if(!arrangementService.isValidArrangement(arrangement.getStartTid(), arrangement.getSluttTid(), arrangement.getNavn(), arrangement.getPris(), adresse.getPoststed(), adresse.getGate(), adresse.getPostNummer())) {
//				session.setAttribute("feilmelding", "Ugyldig arrangement detaljer");
//				return "arrangement";
//			} 
//			else {
			
			Bruker arrangor = brukerRepository.findByMobil((String) session.getAttribute("mobil"));
			boolean opprettArrangement = arrangementService.opprettArrangement(arrangement.getStartTid(),
					arrangement.getSluttTid(), arrangement.getNavn(), arrangement.getUrl(), arrangement.getBilde(),
					arrangement.getPris(), arrangement.getAtype(), arrangement.getAdresse(), adresse.getPoststed(),
					adresse.getGate(), adresse.getPostNummer(), arrangor);

			if (!opprettArrangement) {
				session.setAttribute("feilmelding", "Arrangement finnes allerede");
				return "arrangement";
			}
			}

			session.setAttribute("velykketReg", arrangement.getNavn());

			return "redirect:arrangement";

		}
}
