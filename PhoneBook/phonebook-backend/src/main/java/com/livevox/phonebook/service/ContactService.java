package com.livevox.phonebook.service;

import com.livevox.phonebook.shared.dto.ContactDTO;

import java.util.List;

public interface ContactService {

    ContactDTO createContact(ContactDTO dto);

    List<ContactDTO> findContactByText(String criteria);

    List<ContactDTO> findAllContacts();
}
