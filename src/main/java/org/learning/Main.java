package org.learning;

import org.learning.gate.EntryGate;
import org.learning.gate.ExitGate;
import org.learning.parking.ParkingLot;
import org.learning.parking.ParkingTicket;
import org.learning.parking.display.ParkingLotDisplay;
import org.learning.vehicle.Car;
import org.learning.vehicle.MotorCycle;
import org.learning.vehicle.Vehicle;
import org.learning.vehicle.VehicleType;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Configure the parking lot
        parkingLot.addLevel(1, Map.of(
                VehicleType.CAR, 5,
                VehicleType.MOTORCYCLE, 3,
                VehicleType.TRUCK, 2
        ));
        parkingLot.addDisplay(new ParkingLotDisplay());

        // Create Entry and Exit Gates
        EntryGate entryGate = new EntryGate();
        ExitGate exitGate = new ExitGate();

        // Vehicles
        Vehicle car1 = new Car("CAR123");
        Vehicle bike1 = new MotorCycle("BIKE123");

        // Park Vehicles
        ParkingTicket carTicket = entryGate.processEntry(car1);
        ParkingTicket bikeTicket = entryGate.processEntry(bike1);

        // Unpark Vehicles
        exitGate.processExit(carTicket);
        exitGate.processExit(bikeTicket);
    }
}
