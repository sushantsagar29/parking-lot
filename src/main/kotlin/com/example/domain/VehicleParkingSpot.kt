package com.example.domain

import com.example.exception.NoSpaceAvailableException
import java.util.Stack

interface VehicleParkingSpot {
    val vacantParkingSpots: Stack<ParkingSpot>
    val supportedVehicles: List<Vehicle>

    fun isVehicleSupported(vehicle: Vehicle): Boolean = supportedVehicles.contains(vehicle)

    fun park(): ParkingSpot {
        if (vacantParkingSpots.isEmpty())
            throw NoSpaceAvailableException()
        return vacantParkingSpots.pop()
    }

    fun unPark(parkingSpot: ParkingSpot): ParkingSpot = vacantParkingSpots.push(parkingSpot)
}
