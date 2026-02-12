package com.truck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Order {
    public String id;
    @JsonProperty("payout_cents")
    public long payoutCents;

    @JsonProperty("weight_lbs")
    public int weightLbs;

    @JsonProperty("volume_cuft")
    public int volumeCuft;

    public String origin;
    public String destination;

    @JsonProperty("pickup_date")
    public LocalDate pickupDate;

    @JsonProperty("delivery_date")
    public LocalDate deliveryDate;

    @JsonProperty("is_hazmat")
    public boolean isHazmat;
}
