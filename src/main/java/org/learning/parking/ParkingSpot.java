package org.learning.parking;

import org.learning.vehicle.Vehicle;
import org.learning.vehicle.VehicleType;

import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.spotNumber = spotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void parkVehicle(Vehicle vehicle) {
        try {
            if (reentrantLock.tryLock()) {
                if (!isOccupied() && vehicle.getVehicleType().equals(getVehicleType())) {
                    this.vehicle = vehicle;
                    isOccupied = true;
                } else {
                    throw new IllegalArgumentException("Invalid Vehicle Type or Spot is Occupied");
                }
            } else {
                System.out.println("Spot is currently locked, try later.");
            }
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    public void unParkVehicle() {
        try {
            if (reentrantLock.tryLock()) {
                isOccupied = false;
                vehicle = null;
            } else {
                System.out.println("Spot is currently locked, try later.");
            }
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

