package com.livevox.phonebook.shared.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class ContactDTO implements Serializable {

    private static final long serialVersionUID = -9052379384035592380L;

    @ApiModelProperty(hidden = true)
    private String id;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    private String lastName;

    private String address;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,25}$", message = "Phone number")
    private String phoneNumber;
}