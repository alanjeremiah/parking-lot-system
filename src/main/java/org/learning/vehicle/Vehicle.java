package org.learning.vehicle;

public abstract class Vehicle {
    private final String licenseNo;
    private final VehicleType vehicleType;

    public Vehicle(String licenseNo, VehicleType vehicleType) {
        this.licenseNo = licenseNo;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }
}
