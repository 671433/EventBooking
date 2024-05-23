package com.dat109.hvl.no.eventbooking.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Repository.BrukerRepository;
import com.dat109.hvl.no.eventbooking.Service.BrukerService;
import com.dat109.hvl.no.eventbooking.Service.PassordService;
import com.dat109.hvl.no.eventbooking.Util.LoginUtil;

@Controller
@RequestMapping("/login")
public class LoginController {


	@Autowired
	private BrukerService brukerService;
	
	@Autowired
	private PassordService passordService;
	
	@GetMapping
	public String visInnloggingSide() {
		
		return"login";
		
	}

	@PostMapping
	 public String provAaLoggeInn(@RequestParam String mobil, String passord, HttpServletRequest request,
	            HttpSession session) {

	        
	        Bruker funnet = brukerService.finnBruker(mobil);
	        
	        if (funnet == null) {
	           session.setAttribute("beskjed_message", "Finner ingen brukere med dette telefonnummeret");
	            return "redirect:login";
	        } else if (!(passordService.erKorrektPassord(passord, funnet.getSalt(),
	                funnet.getHash()))) {
	            session.setAttribute("beskjed_message", "Ugyldig passord");
	            return "redirect:login";
	        }
	       
	        LoginUtil.loggInnBruker(request, mobil);
	        LoginUtil.setMeldtpaa(funnet);
	        
	        
	        return "redirect:sokeside";
	    }
}
