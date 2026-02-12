package com.truck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Truck {
    public String id;
    @JsonProperty("max_weight_lbs")
    public int maxWeightLbs;

    @JsonProperty("max_volume_cuft")
    public int maxVolumeCuft;
}
