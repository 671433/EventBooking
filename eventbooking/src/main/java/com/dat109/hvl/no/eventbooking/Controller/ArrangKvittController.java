package com.dat109.hvl.no.eventbooking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arrangementKvitteringside")
public class ArrangKvittController {
	
	@GetMapping
	public String getRegKvittering() {
		return "arrangementKvitteringside";
	}
	

}
