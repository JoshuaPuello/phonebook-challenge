package com.livevox.phonebook.service.impl;

import com.livevox.phonebook.exception.ContactServiceException;
import com.livevox.phonebook.model.Contact;
import com.livevox.phonebook.repository.ContactRepository;
import com.livevox.phonebook.service.ContactService;
import com.livevox.phonebook.shared.dto.ContactDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.livevox.phonebook.shared.constants.ErrorMessagesConstant.FIELD_ALREADY_EXISTS;
import static com.livevox.phonebook.shared.constants.ErrorMessagesConstant.NO_RECORD_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        validateRulesForRequestedModel(dto);
        Contact contact = modelMapper.map(dto, Contact.class);
        Contact storedContact = contactRepository.save(contact);
        return modelMapper.map(storedContact, ContactDTO.class);
    }

    @Override
    public List<ContactDTO> findContactByText(String text) {
        List<Contact> contactList = contactRepository.findContactByAnyColumnContainingText(text.toLowerCase());
        if (CollectionUtils.isEmpty(contactList)) {
            throw new ContactServiceException(NO_RECORD_FOUND, NOT_FOUND);
        }
        return mapCollectionEntityToDto(contactList);
    }

    @Override
    public List<ContactDTO> findAllContacts() {
        List<Contact> contactList = contactRepository.findAll();
        if (CollectionUtils.isEmpty(contactList)) {
            throw new ContactServiceException(NO_RECORD_FOUND, NOT_FOUND);
        }
        return mapCollectionEntityToDto(contactList);
    }

    private void validateRulesForRequestedModel(ContactDTO dto) {
        List<String> exceptionMsgList = new ArrayList<>();

        if (contactRepository.findContactByEmail(dto.getEmail()) != null) {
            exceptionMsgList.add(String.format(FIELD_ALREADY_EXISTS, "Email", dto.getEmail()));
        }
        if (contactRepository.findContactByPhoneNumber(dto.getPhoneNumber()) != null) {
            exceptionMsgList.add(String.format(FIELD_ALREADY_EXISTS, "PhoneNumber", dto.getPhoneNumber()));
        }

        if (!CollectionUtils.isEmpty(exceptionMsgList)) {
            throw new ContactServiceException(String.join(", ", exceptionMsgList), CONFLICT);
        }
    }

    private List<ContactDTO> mapCollectionEntityToDto(List<Contact> contactList) {
        return contactList.stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }
}
