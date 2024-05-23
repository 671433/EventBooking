package com.dat109.hvl.no.eventbooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.dat109.hvl.no.eventbooking.Model.Bruker;


@Repository
public interface BrukerRepository extends JpaRepository<Bruker, String> {

	public Bruker findByMobil(String mobil);
	
//	Optional<Bruker> findByMobil(String mobil);
}
