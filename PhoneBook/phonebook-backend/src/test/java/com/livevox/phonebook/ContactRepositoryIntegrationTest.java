package com.livevox.phonebook;

import com.livevox.phonebook.model.Contact;
import com.livevox.phonebook.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryIntegrationTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void givenExistingDataOnDB_whenFindAllContacts_thenReturnContactList() {
        List<Contact> contacts = contactRepository.findAll();
        assertNotNull(contacts);
        assertEquals(5, contacts.size());
    }

    @Test
    public void givenExistingTextOnDB_whenFindContactByText_thenReturnContactList() {
        List<Contact> contacts = contactRepository.findContactByAnyColumnContainingText("onslow");
        assertNotNull(contacts);
        assertEquals(2, contacts.size());
    }

    @Test
    public void givenContact_whenSave_thenSaveContactOnDB() {
        Contact contact = Contact.builder()
                .id("f082fhb").firstName("User")
                .lastName("Unknown").address("Some Address #4-5")
                .email("user.unk@gmail.com").phoneNumber("5-666-556")
                .build();

        Contact savedContact = contactRepository.save(contact);
        Contact foundByEmail = contactRepository.findContactByEmail("user.unk@gmail.com");

        assertEquals("User", savedContact.getFirstName());
        assertEquals("User", foundByEmail.getFirstName());
        assertEquals("Unknown", savedContact.getLastName());
        assertEquals("5-666-556", savedContact.getPhoneNumber());
    }

}
