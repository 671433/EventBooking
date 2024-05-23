package com.dat109.hvl.no.eventbooking.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dat109.hvl.no.eventbooking.Util.LoginUtil;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@GetMapping
	public String visInnloggingSide() {
		
		return "/";
		
	}
	
	
	@PostMapping
	public String loggUt (HttpServletRequest request, RedirectAttributes ra) {
		LoginUtil.loggUtBruker(request.getSession());
		ra.addFlashAttribute("beskjed_message", "Du er logget ut");
		return "redirect:/";
	}

}
