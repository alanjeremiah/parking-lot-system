package org.learning.gate;

import org.learning.parking.ParkingLot;
import org.learning.parking.ParkingTicket;
import org.learning.vehicle.Vehicle;

public class EntryGate {
    private final ParkingLot parkingLot;

    public EntryGate() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public ParkingTicket processEntry(Vehicle vehicle) {
        try {
            return parkingLot.parkVehicle(vehicle);
        } catch (IllegalStateException e) {
            System.out.println("Entry Failed: " + e.getMessage());
            return null;
        }
    }
}
