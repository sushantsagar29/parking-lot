package com.example.domain

import java.util.Stack

class TwoWheelerParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override fun getSupportedVehicles() = listOf(
        Vehicle.MOTORCYCLE,
        Vehicle.SCOOTER
    )
}