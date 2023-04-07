package com.example.domain

import java.util.Stack

class LMVParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override val supportedVehicles: List<Vehicle> by lazy {
        listOf(
            Vehicle.BUS,
            Vehicle.TRUCK
        )
    }
}