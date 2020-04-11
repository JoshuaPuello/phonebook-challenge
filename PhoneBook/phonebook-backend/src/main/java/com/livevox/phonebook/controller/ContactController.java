package com.livevox.phonebook.controller;

import com.livevox.phonebook.service.ContactService;
import com.livevox.phonebook.shared.dto.ContactDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ContactDTO createContact(@RequestBody ContactDTO dto) {
        return contactService.createContact(dto);
    }

    @GetMapping
    public List<ContactDTO> findContactByText(@RequestParam String text) {
        return contactService.findContactByText(text);
    }

    @GetMapping("/all")
    public List<ContactDTO> findAllContacts() {
        return contactService.findAllContacts();
    }
}
