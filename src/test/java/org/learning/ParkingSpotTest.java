package org.learning;

import org.junit.jupiter.api.Test;
import org.learning.parking.ParkingSpot;
import org.learning.vehicle.Car;
import org.learning.vehicle.VehicleType;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

    @Test
    void testParkVehicle_Success() {
        var spot = new ParkingSpot(1, VehicleType.CAR);
        var car = new Car("CAR123");

        assertDoesNotThrow(() -> spot.parkVehicle(car), "Parking should not throw an exception");
        assertTrue(spot.isOccupied(), "Spot should be marked as occupied");
    }

    @Test
    void testParkVehicle_InvalidType() {
        var spot = new ParkingSpot(1, VehicleType.MOTORCYCLE);
        var car = new Car("CAR123");

        assertThrows(IllegalArgumentException.class, () -> spot.parkVehicle(car),
                "Should throw exception for invalid vehicle type");
    }

    @Test
    void testUnParkVehicle() {
        var spot = new ParkingSpot(1, VehicleType.CAR);
        var car = new Car("CAR123");
        spot.parkVehicle(car);

        assertDoesNotThrow(spot::unParkVehicle, "Unparking should not throw an exception");
        assertFalse(spot.isOccupied(), "Spot should be marked as free");
    }
}
