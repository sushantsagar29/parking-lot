package com.example.domain

import java.util.Stack

class LMVParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override fun getSupportedVehicles() = listOf(
        Vehicle.BUS,
        Vehicle.TRUCK
    )
}