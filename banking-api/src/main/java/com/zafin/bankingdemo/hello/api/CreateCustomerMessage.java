package com.zafin.bankingdemo.hello.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.time.LocalDate;

@Value
@JsonDeserialize
public class CreateCustomerMessage {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;

    @JsonCreator
    public CreateCustomerMessage(String firstName, String lastName, LocalDate dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

    }
}
