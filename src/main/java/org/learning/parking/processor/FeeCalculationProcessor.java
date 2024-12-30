package org.learning.parking.processor;

import org.learning.parking.ParkingTicket;
import org.learning.vehicle.VehicleType;

public class FeeCalculationProcessor {
    public static double calculateFee(ParkingTicket ticket) {
        VehicleType type = ticket.getVehicle().getVehicleType();
        long durationMinutes = ticket.getDurationMinutes();

        double ratePerHour = switch (type) {
            case CAR -> 10.0;
            case MOTORCYCLE -> 5.0;
            case TRUCK -> 20.0;
        };

        return (durationMinutes / 60.0) * ratePerHour;
    }
}

