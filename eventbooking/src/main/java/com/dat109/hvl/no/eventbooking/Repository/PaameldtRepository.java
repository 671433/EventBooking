package com.dat109.hvl.no.eventbooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dat109.hvl.no.eventbooking.Model.Arrangement;
import com.dat109.hvl.no.eventbooking.Model.Pameldt;

@Repository
public interface PaameldtRepository extends JpaRepository<Pameldt, Integer>{

	public Pameldt findByPid(Integer id);

	boolean existsPameldtByBruker(String mobil);
	
	boolean existsPameldtByArrangement(Integer id);

	
	
	
}