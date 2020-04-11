package com.livevox.phonebook.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContactDTO implements Serializable {

    private static final long serialVersionUID = -9052379384035592380L;

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
}