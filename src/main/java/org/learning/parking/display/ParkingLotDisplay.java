package org.learning.parking.display;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotDisplay {
    private final List<String> updates = new ArrayList<>();

    public void notify(String message) {
        updates.add(message);
        System.out.println("Display Update: " + message);
    }
}
