package org.learning.gate;

import org.learning.parking.ParkingLot;
import org.learning.parking.ParkingTicket;

public class ExitGate {
    private final ParkingLot parkingLot;

    public ExitGate() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public void processExit(ParkingTicket ticket) {
        try {
            parkingLot.unParkVehicle(ticket);
        } catch (IllegalStateException e) {
            System.out.println("Exit Failed: " + e.getMessage());
        }
    }
}
