package org.learning.parking;

import org.learning.vehicle.Vehicle;
import org.learning.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLevel {

    private final int floor;
    private final Map<VehicleType, List<ParkingSpot>> availableSpots;

    ParkingLevel(int floor, Map<VehicleType, Integer> spotDistribution) {
        this.floor = floor;
        this.availableSpots = new HashMap<>();

        int spotNumber = 1;
        for (Map.Entry<VehicleType, Integer> entry: spotDistribution.entrySet()) {
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for(int i = 0; i < entry.getValue(); i++) {
                parkingSpots.add(new ParkingSpot(spotNumber++, entry.getKey()));
            }
            availableSpots.put(entry.getKey(), parkingSpots);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        List<ParkingSpot> parkingSpots = availableSpots.get(vehicle.getVehicleType());
        if (parkingSpots == null || parkingSpots.isEmpty()) {
            return false;
        }

        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : availableSpots.get(vehicle.getVehicleType())) {
            if (spot.isOccupied() && spot.getVehicle().equals(vehicle)) {
                spot.unParkVehicle();
                return true;
            }
        }
        return false;
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType vehicleType) {
        return availableSpots.get(vehicleType);
    }

}
