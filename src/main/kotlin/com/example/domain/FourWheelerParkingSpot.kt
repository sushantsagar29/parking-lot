package com.example.domain

import java.util.Stack

class FourWheelerParkingSpot(
    override val vacantParkingSpots: Stack<ParkingSpot>
) : VehicleParkingSpot {
    override fun getSupportedVehicles() = listOf(
        Vehicle.CAR,
        Vehicle.SUV
    )
}