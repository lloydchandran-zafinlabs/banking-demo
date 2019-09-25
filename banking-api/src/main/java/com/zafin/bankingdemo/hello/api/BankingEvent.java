package com.zafin.bankingdemo.hello.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = BankingEvent.CustomerCreated.class, name = "customer-created")
})
public interface BankingEvent {

   String getCustomerId();

  @Value
  final class CustomerCreated implements BankingEvent {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String customerId;

    @JsonCreator
    public CustomerCreated(String customerId,String firstName, String lastName, LocalDate dateOfBirth){
      this.customerId = customerId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.dateOfBirth = dateOfBirth;


    }

  }
}
