package org.learning.parking;

import org.learning.parking.display.ParkingLotDisplay;
import org.learning.parking.processor.FeeCalculationProcessor;
import org.learning.vehicle.Vehicle;
import org.learning.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private final List<ParkingLevel> levels;
    private final List<ParkingLotDisplay> displays;

    private ParkingLot() {
        levels = new ArrayList<>();
        displays = new ArrayList<>();
    }

    private static class ParkingLotHelper {
        private static final ParkingLot instance = new ParkingLot();
    }

    public static ParkingLot getInstance() {
        return ParkingLotHelper.instance;
    }

    public void addLevel(int floor, Map<VehicleType, Integer> spotDistribution) {
        levels.add(new ParkingLevel(floor, spotDistribution));
    }

    public void addDisplay(ParkingLotDisplay display) {
        displays.add(display);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                ParkingSpot spot = findSpot(level, vehicle); // Locate the parked spot
                notifyDisplays("Vehicle parked: " + vehicle.getVehicleType() + " in spot " + spot.getSpotNumber());
                return new ParkingTicket(vehicle, spot);
            }
        }
        throw new IllegalStateException("No available parking spots for vehicle type: " + vehicle.getVehicleType());
    }

    public void unParkVehicle(ParkingTicket ticket) {
        boolean isUnparked = false;
        for (ParkingLevel level : levels) {
            if (level.unParkVehicle(ticket.getVehicle())) {
                isUnparked = true;
                break;
            }
        }
        if (isUnparked) {
            ticket.markExit();
            double fee = FeeCalculationProcessor.calculateFee(ticket);
            notifyDisplays("Vehicle unparked: " + ticket.getVehicle().getVehicleType() + ". Fee: $" + fee);
        } else {
            throw new IllegalStateException("Vehicle not found in the parking lot.");
        }
    }

    private void notifyDisplays(String message) {
        for (ParkingLotDisplay display : displays) {
            display.notify(message);
        }
    }

    private ParkingSpot findSpot(ParkingLevel parkingLevel, Vehicle vehicle) {
        for (ParkingSpot spot : parkingLevel.getAvailableSpots(vehicle.getVehicleType())) {
            if (spot.getVehicle() == vehicle) {
                return spot;
            }
        }
        throw new IllegalStateException("No matching spot found for vehicle.");
    }
}
