package com.zafin.bankingdemo.hello.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

@Value
@JsonDeserialize
public class CustomerId {
    private final String customerId;

    @JsonCreator
    public CustomerId(String customerId) {
        this.customerId = customerId;
    }
}
