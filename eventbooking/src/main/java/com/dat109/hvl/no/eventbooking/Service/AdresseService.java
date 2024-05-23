package com.dat109.hvl.no.eventbooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat109.hvl.no.eventbooking.Model.Adresse;
import com.dat109.hvl.no.eventbooking.Repository.AdresseRepository;

@Service
public class AdresseService {
	
	@Autowired
	private AdresseRepository addRepo;
	
	public List<String> getAllePoststed(){
		
		List<Adresse> alleAddresser = addRepo.findAll();
		
		List<String> poststeder = alleAddresser.stream()
				.map(a -> a.getPoststed())
				.distinct()
				.toList();
	
		return poststeder;
		
	}
	
	

}
