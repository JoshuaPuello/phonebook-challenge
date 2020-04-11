package com.livevox.phonebook.service.impl;

import com.livevox.phonebook.model.Contact;
import com.livevox.phonebook.repository.ContactRepository;
import com.livevox.phonebook.service.ContactService;
import com.livevox.phonebook.shared.dto.ContactDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public ContactDTO createContact(ContactDTO dto) {
        Contact contact = modelMapper.map(dto, Contact.class);
        Contact storedContact = contactRepository.save(contact);
        return modelMapper.map(storedContact, ContactDTO.class);
    }

    @Override
    public List<ContactDTO> findContactByText(String text) {
        List<Contact> contactList = contactRepository.findContactByAnyColumnContainingText(text);
        return mapEntityListToDto(contactList);
    }

    @Override
    public List<ContactDTO> findAllContacts() {
        List<Contact> contactList = contactRepository.findAll();
        return mapEntityListToDto(contactList);
    }

    private List<ContactDTO> mapEntityListToDto(List<Contact> contactList) {
        return contactList.stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }
}
