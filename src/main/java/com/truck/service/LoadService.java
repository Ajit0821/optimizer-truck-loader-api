package com.truck.service;

import com.truck.dto.Response;
import com.truck.model.Order;
import com.truck.model.Truck;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadService {
    public Response optimize(Truck truck, List<Order> orders) {
        int n = orders.size();
        long bestPayout = 0;
        int bestMask = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            int weight = 0;
            int volume = 0;
            long payout = 0;
            boolean hazmatPresent = false;
            String origin = null;
            String destination = null;

            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    Order o = orders.get(i);

                    weight += o.weightLbs;
                    volume += o.volumeCuft;
                    payout += o.payoutCents;

                    if (weight > truck.maxWeightLbs || volume > truck.maxVolumeCuft) {
                        valid = false;
                        break;
                    }

                    if (origin == null) {
                        origin = o.origin;
                        destination = o.destination;
                    } else if (!origin.equals(o.origin) || !destination.equals(o.destination)) {
                        valid = false;
                        break;
                    }

                    if (o.isHazmat) {
                        if (Integer.bitCount(mask) > 1) {
                            valid = false;
                            break;
                        }
                    }

                }
            }

            if (valid && payout > bestPayout) {
                bestPayout = payout;
                bestMask = mask;
            }
        }

        return buildResponse(truck, orders, bestMask);
    }

    private Response buildResponse(Truck truck, List<Order> orders, int mask) {
        Response res = new Response();
        res.truckId = truck.id;
        res.selectedOrderIds = new ArrayList<>();

        int weight = 0;
        int volume = 0;
        long payout = 0;

        for (int i = 0; i < orders.size(); i++) {
            if ((mask & (1 << i)) != 0) {
                Order o = orders.get(i);
                res.selectedOrderIds.add(o.id);
                weight += o.weightLbs;
                volume += o.volumeCuft;
                payout += o.payoutCents;
            }
        }
        res.totalWeightLbs = weight;
        res.totalVolumeCuft = volume;
        res.totalPayoutCents = payout;
        res.utilizationWeightPercent = round((weight * 100.0) /  truck.maxWeightLbs);
        res.utilizationVolumePercent = round((volume * 100.0) / truck.maxVolumeCuft);

        return res;
    }
    private double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
