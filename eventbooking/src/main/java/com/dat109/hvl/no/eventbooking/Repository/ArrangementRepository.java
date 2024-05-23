package com.dat109.hvl.no.eventbooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dat109.hvl.no.eventbooking.Model.AType;
import com.dat109.hvl.no.eventbooking.Model.Arrangement;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement, Integer> {

	
	Arrangement findById(int id);
	Arrangement getById(int id); 
	
}

