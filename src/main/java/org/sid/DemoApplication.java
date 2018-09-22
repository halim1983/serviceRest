package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private ContactRepository contactrepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactrepository.save(new Contact("Halim", "AMMOUR", df.parse("05/10/1983"), "ammour.halim@yahoo.fr", 054377));
		
		contactrepository.findAll().forEach(c->{System.out.println(c.getNom());});
	}
}
