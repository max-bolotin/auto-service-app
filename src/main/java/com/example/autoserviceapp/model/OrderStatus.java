package com.example.autoserviceapp.model;

public enum OrderStatus {
    TAKEN("Taken"),
    INPROCESS("InProcess"),
    FULFILLEDSUCCESSFULLY("FulfilledSuccessfully"),
    FULFILLEDUNSUCCESSFULLY("FulfilledUnsuccessfully"),
    PAID("Paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
