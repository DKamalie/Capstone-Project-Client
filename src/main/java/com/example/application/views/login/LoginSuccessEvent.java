package com.example.application.views.login;

import com.example.application.domain.LoyaltyCustomer;

public class LoginSuccessEvent {
    private final LoyaltyCustomer customer;

    public LoginSuccessEvent(LoyaltyCustomer customer) {
        this.customer = customer;
    }

    public LoyaltyCustomer getCustomer() {
        return customer;
    }
}
