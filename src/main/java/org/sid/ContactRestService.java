package org.sid;


import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ContactRestService {

	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	@Test
	public List<Contact> getContacts() {
		List<Contact> listContacts = contactRepository.findAll();
		assertThat(listContacts, not(IsEmptyCollection.empty()));
		return listContacts;

	}

	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
	public Contact getContactById(@PathVariable Long id) {
		return contactRepository.findOne(id);

	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact c) {
		return contactRepository.save(c);

	}

	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
	public boolean supprimerContactById(@PathVariable Long id) {
		contactRepository.delete(id);
		return true;
	}
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
	public Contact updateContact(@PathVariable Long id, @RequestBody Contact c) {
		c.setId(id);
		return contactRepository.save(c);
	}
	@RequestMapping(value = "/chercher", method=RequestMethod.GET)
	public Page<Contact> chercherContact(
			@RequestParam(name="mc", defaultValue="") String mc, 
			@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size) {
		return contactRepository.chercherContact("%"+mc+"%", new PageRequest(page, size));
	}
}
