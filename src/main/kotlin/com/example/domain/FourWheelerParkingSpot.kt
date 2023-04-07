package com.example.domain

import java.util.Stack

class FourWheelerParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override val supportedVehicles: List<Vehicle> by lazy {
        listOf(
            Vehicle.CAR,
            Vehicle.SUV
        )
    }

    override fun isVehicleSupported(vehicle: Vehicle): Boolean = supportedVehicles.contains(vehicle)
}