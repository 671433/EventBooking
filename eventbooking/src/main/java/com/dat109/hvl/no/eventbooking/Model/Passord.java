package com.dat109.hvl.no.eventbooking.Model;

import com.dat109.hvl.no.eventbooking.Service.PassordService;

/*
 * Klasse for Passord
 */

public class Passord {
	
    private String hash;
    private String salt;
    
    public Passord() {}
    
    public Passord(String klartekst) {
        PassordService service = new PassordService();
        
        salt = service.genererTilfeldigSalt();
        hash = service.hashMedSalt(klartekst, salt);
        
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    

}
