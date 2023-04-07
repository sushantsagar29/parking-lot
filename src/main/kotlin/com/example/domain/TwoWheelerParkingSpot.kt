package com.example.domain

import java.util.Stack

class TwoWheelerParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override val supportedVehicles: List<Vehicle> by lazy {
        listOf(
            Vehicle.MOTORCYCLE,
            Vehicle.SCOOTER
        )
    }
}