package com.dat109.hvl.no.eventbooking.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.AdresseRepository;
import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Service.AdresseService;
import com.dat109.hvl.no.eventbooking.Service.ArrangementService;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;
import com.dat109.hvl.no.eventbooking.Service.PaameldtService;
import com.dat109.hvl.no.eventbooking.Service.SookeService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;


@Controller
public class SookeSideController {

	@Autowired
	private BrukerService brukerService;
	
	@Autowired
	private SookeService soek;
	
	@Autowired
	private PaameldtService paService;
	
	@Autowired
	private ArrangementService arService;
	@Autowired 
	private AdresseRepository addRepo;


	@GetMapping("/sokeside")
	public String getSokeSide( HttpServletRequest request, HttpSession session) {

		List<Adresse> alleAddresser = addRepo.findAll();
		List<String> poststeder = alleAddresser.stream().map(x -> x.getPoststed()).distinct().collect(Collectors.toList());
		
		request.setAttribute("poststeder", poststeder);
		request.setAttribute("types", AType.values());
		if (LoginUtil.erBrukerInnlogget(session)) {
			String mobil = (String) session.getAttribute("mobil");
			
			Bruker innlogget = brukerService.finnBruker(mobil);
			
			
			String navn = innlogget.getFornavn() +" " + innlogget.getEtternavn();
			session.setAttribute("navn", navn);
		}
		
		
		
		
		

		
		return "sokeside";

	}

	
	@PostMapping("sokeside/sok")
	public String getSokeResultat(HttpSession session, @RequestParam(name = "Type") String atype,
			@RequestParam(name = "Sted") String sted, @RequestParam(name = "dato") String dato)

	{


		List<Arrangement> arrangement = soek.sook(atype, sted, dato);

		session.setAttribute("Arrangement", arrangement);
		List<Integer> pameldte = new ArrayList<>();

		session.removeAttribute("valgtArrangement");

		for (Arrangement a : arrangement) {
			if (LoginUtil.erBrukerInnlogget(session)) {

				Integer aId = a.getId();
				String mobil = (String) session.getAttribute("mobil");

				if (paService.erBrukerPameldt(mobil, aId)) {
					pameldte.add(aId);
				}
				if (!paService.erBrukerPameldt(mobil, aId)) {

				}
				
			}
			session.setAttribute("pameldte", pameldte);
			session.setAttribute("soker", "soker");

		}

		return "redirect:/sokeside";

	}

	@PostMapping("sokeside/meldPa")
	public String meldPaArrangement(HttpSession session, RedirectAttributes a, @RequestParam(name = "arrId") String arrId) {

		if (LoginUtil.erBrukerInnlogget(session)) {

			Integer id = Integer.parseInt(arrId);

			String mobil = (String) session.getAttribute("mobil");
			
			

			if (paService.erBrukerPameldt(mobil, id)) {
				session.setAttribute("Feilmelding", "Du er alleredemeldt på");
			}
			if (!paService.erBrukerPameldt(mobil, id)) {
				
				paService.meldPa(mobil, id);
				session.removeAttribute("Arrangement");

				String aNavn = arService.finnArrangement(id).getNavn();
				session.setAttribute("valgtArrangement", aNavn);
				
			}

	
		} else {
			a.addFlashAttribute("login", "Du må logge inn for å melde deg på et arrangement"); 
		}

		return "redirect:/sokeside";
	}


	@GetMapping("sokeside/minearrangement")
	public String minearrangement(HttpSession session) {
		List<Arrangement> arrangement = soek.sook("", "", "");

		List<Arrangement> mineArrangement = new ArrayList<>();
		
		List<Integer> pameldte = new ArrayList<>();

		session.removeAttribute("valgtArrangement");

		for (Arrangement a : arrangement) {
			if (LoginUtil.erBrukerInnlogget(session)) {

				Integer aId = a.getId();
				String mobil = (String) session.getAttribute("mobil");

			
				if (paService.erBrukerPameldt(mobil, aId)) {
					mineArrangement.add(a);
					pameldte.add(aId);
				}
				if (!paService.erBrukerPameldt(mobil, aId)) {

				}

			}
			session.setAttribute("pameldte", pameldte);

		}
		session.setAttribute("soker", "soker");

		session.setAttribute("Arrangement", mineArrangement);
		return "redirect:/sokeside";

	}
}

