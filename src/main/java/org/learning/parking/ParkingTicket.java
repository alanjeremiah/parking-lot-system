package org.learning.parking;

import org.learning.vehicle.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket {
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    public long getDurationMinutes() {
        return Duration.between(entryTime, exitTime).toMinutes();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
