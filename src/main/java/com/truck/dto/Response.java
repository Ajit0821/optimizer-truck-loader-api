package com.truck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({
        "truck_id",
        "selected_order_ids",
        "total_payout_cents",
        "total_weight_lbs",
        "total_volume_cuft",
        "utilization_weight_percent",
        "utilization_volume_percent"
})
public class Response {
    @JsonProperty("truck_id")
    public String truckId;

    @JsonProperty("selected_order_ids")
    public List<String> selectedOrderIds;

    @JsonProperty("total_payout_cents")
    public long totalPayoutCents;

    @JsonProperty("total_weight_lbs")
    public int totalWeightLbs;

    @JsonProperty("total_volume_cuft")
    public int totalVolumeCuft;

    @JsonProperty("utilization_weight_percent")
    public double utilizationWeightPercent;

    @JsonProperty("utilization_volume_percent")
    public double utilizationVolumePercent;
}
