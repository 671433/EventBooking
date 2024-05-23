package com.dat109.hvl.no.eventbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventbookingApplication.class, args);
	}

}

// Denne klassen blir aktivert når vi vil gjøe deploy 


//package com.dat109.hvl.no.eventbooking;
//
//
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//@SpringBootApplication
//public class EventbookingApplication extends SpringBootServletInitializer
//{
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder appBuilder)
//   {
//        return appBuilder.sources(EventbookingApplication.class);
//    }
//
//    public static void main(String[] args) throws Exception
//   {
//        SpringApplication.run(EventbookingApplication.class, args);
//    }
//}

