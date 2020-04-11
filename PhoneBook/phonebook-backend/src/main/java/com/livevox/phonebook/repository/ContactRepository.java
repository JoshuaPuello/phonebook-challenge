package com.livevox.phonebook.repository;

import com.livevox.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("SELECT c FROM Contact c WHERE c.firstName LIKE %?1% " +
            "OR c.lastName LIKE %?1% OR c.address LIKE %?1% " +
            "OR c.email LIKE %?1% OR c.phoneNumber LIKE %?1%")
    List<Contact> findContactByAnyColumnContainingText(String text);
}
