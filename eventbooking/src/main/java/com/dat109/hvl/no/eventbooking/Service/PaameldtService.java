package com.dat109.hvl.no.eventbooking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Bruker;
import com.dat109.hvl.no.eventbooking.Model.Pameldt;
import com.dat109.hvl.no.eventbooking.Repository.AdresseRepository;

import com.dat109.hvl.no.eventbooking.Repository.PaameldtRepository;

@Service
public class PaameldtService {

	@Autowired
	private PaameldtRepository pameldtRepository;
	
	@Autowired
	private BrukerService brukService;
	
	@Autowired
	private ArrangementService arrService;

	
	
	public Pameldt meldPa(String mobil, Integer arrId) {
		
		Bruker bruker = brukService.finnBruker(mobil);	
		Arrangement arrangement = arrService.finnArrangement(arrId);
		
		Pameldt pameldt = new Pameldt(bruker, arrangement);
		return pameldtRepository.save(pameldt);
		
	}

	public Pameldt finnPameldt(int id){
		
		return pameldtRepository.findByPid(id);
		
	}
	public boolean finnesBruker(String mobil) {
		return pameldtRepository.existsPameldtByBruker(mobil);
	}
	public boolean finnesArrangement(Integer id) {
		return pameldtRepository.existsPameldtByArrangement(id);
	}
	
	public boolean erBrukerPameldt(String mobil, int id) {
	    boolean finnes = false;
	    
	  
	    for (Pameldt pameldt : pameldtRepository.findAll()) {
	        if (pameldt.getArrangement().getId() == id && pameldt.getBruker().getMobil().equals(mobil)) {
	           
	            finnes = true;
	            break;
	        }
	    }
	    
	    return finnes;
	}
	
	
}
