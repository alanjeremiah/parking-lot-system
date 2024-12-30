package org.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learning.parking.ParkingLot;
import org.learning.parking.ParkingSpot;
import org.learning.parking.ParkingTicket;
import org.learning.vehicle.Car;
import org.learning.vehicle.VehicleType;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setup() {
        parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(1, Map.of(
                VehicleType.CAR, 5,
                VehicleType.MOTORCYCLE, 3
        ));
    }

    @Test
    void testParkVehicle_Success() {
        var car = new Car("CAR123");
        var ticket = parkingLot.parkVehicle(car);
        assertNotNull(ticket, "Parking ticket should not be null");
        assertEquals("CAR123", ticket.getVehicle().getLicenseNo(), "License number should match");
    }

    @Test
    void testParkVehicle_NoSpotsAvailable() {
        // Fill all spots
        for (int i = 0; i < 5; i++) {
            parkingLot.parkVehicle(new Car("CAR" + i));
        }

        var car = new Car("EXTRA_CAR");
        assertThrows(IllegalStateException.class, () -> parkingLot.parkVehicle(car),
                "Should throw exception when no spots are available");
    }

    @Test
    void testUnParkVehicle_Success() {
        var car = new Car("CAR123");
        var ticket = parkingLot.parkVehicle(car);

        assertDoesNotThrow(() -> parkingLot.unParkVehicle(ticket), "Unparking should not throw an exception");
    }

    @Test
    void testUnParkVehicle_NotFound() {
        var car = new Car("CAR123");
        var ticket = new ParkingTicket(car, new ParkingSpot(1, VehicleType.CAR));

        assertThrows(IllegalStateException.class, () -> parkingLot.unParkVehicle(ticket),
                "Should throw exception when vehicle is not found in the parking lot");
    }
}
