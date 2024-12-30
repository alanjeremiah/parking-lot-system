package org.learning;

import org.junit.jupiter.api.Test;
import org.learning.parking.ParkingSpot;
import org.learning.vehicle.Car;
import org.learning.vehicle.VehicleType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotLockTest {

    @Test
    void testConcurrentParking() throws InterruptedException {
        var spot = new ParkingSpot(1, VehicleType.CAR);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable parkTask = () -> {
            try {
                spot.parkVehicle(new Car("CAR" + Thread.currentThread().getId()));
            } catch (IllegalArgumentException ignored) {
            }
        };

        // Run two threads attempting to park
        executor.submit(parkTask);
        executor.submit(parkTask);

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Executor should terminate");

        // Only one thread should succeed
        assertTrue(spot.isOccupied(), "Spot should be occupied by one car");
    }
}
