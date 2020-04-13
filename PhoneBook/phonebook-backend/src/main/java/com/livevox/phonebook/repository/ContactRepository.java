package com.livevox.phonebook.repository;

import com.livevox.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("SELECT c FROM Contact c WHERE LOWER(c.firstName) LIKE %?1% " +
            "OR LOWER(c.lastName) LIKE %?1% OR LOWER(c.address) LIKE %?1% " +
            "OR LOWER(c.email) LIKE %?1% OR LOWER(c.phoneNumber) LIKE %?1%")
    List<Contact> findContactByAnyColumnContainingText(String text);

    Contact findContactByEmail(String email);

    Contact findContactByPhoneNumber(String phoneNumber);
}
