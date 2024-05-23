package com.dat109.hvl.no.eventbooking.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dat109.hvl.no.eventbooking.Model.Bruker;

public class LoginUtil {

	private static Bruker meldtpaa;

	public static Bruker getMeldtpaa() {
		return meldtpaa;
	}

	public static void setMeldtpaa(Bruker m) {
		meldtpaa = m;
	}

	public static void loggUtBruker(HttpSession session) {
		session.invalidate();
	}

	public static void loggInnBruker(HttpServletRequest request, String mobil) {

		loggUtBruker(request.getSession());

		HttpSession sesjon = request.getSession();
		sesjon.setMaxInactiveInterval(60*7);
		sesjon.setAttribute("mobil", mobil);
		sesjon.setAttribute("", sesjon);
	}

	public static boolean erBrukerInnlogget(HttpSession session) {

		return session != null && session.getAttribute("mobil") != null;
	}
}
