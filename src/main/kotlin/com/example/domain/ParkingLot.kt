package com.example.domain

import com.example.exception.VehicleNotSupportedException
import com.example.model.ParkingReceipt
import com.example.model.ParkingTicket
import java.time.Instant

class ParkingLot(
    val vehicleParkingSpots: List<VehicleParkingSpot>,
    val spotNumberToBookedParkingSpotMap: HashMap<Int, ParkingSpot>,
    val feesCalculator: FeesCalculator
) {
    private var ticketNumberCounter: Int = 0
    private var receiptNumberCounter: Int = 0


    fun park(vehicleType: Vehicle): ParkingTicket {
        val bookedParkingSpot = bookParkingSpot(vehicleType)
        ticketNumberCounter += 1
        return ParkingTicket(ticketNumberCounter, bookedParkingSpot.spotNumber, vehicleType)
    }

    fun unPark(parkingTicket: ParkingTicket): ParkingReceipt {
        receiptNumberCounter += 1
        val bookingEndTime = Instant.now()

        vacateParkingSpot(parkingTicket)

        return ParkingReceipt(
            receiptNumber = receiptNumberCounter,
            bookingStartTime = parkingTicket.bookingStartTime,
            bookingEndTime = bookingEndTime,
            fees = calculateFees(parkingTicket, bookingEndTime)
        )
    }

    private fun calculateFees(parkingTicket: ParkingTicket, bookingEndTime: Instant): Int =
        feesCalculator.calculate(parkingTicket.vehicle, parkingTicket.bookingStartTime, bookingEndTime)


    private fun bookParkingSpot(vehicleType: Vehicle): ParkingSpot {
        val vehicleParkingSpot = getVehicleParkingSpot(vehicleType)
        val bookedParkingSpot = vehicleParkingSpot.park()
        spotNumberToBookedParkingSpotMap[bookedParkingSpot.spotNumber] = bookedParkingSpot
        return bookedParkingSpot
    }

    private fun vacateParkingSpot(parkingTicket: ParkingTicket) {
        val vehicleParkingSpot = getVehicleParkingSpot(parkingTicket.vehicle)
        val bookedParkingSpot = spotNumberToBookedParkingSpotMap[parkingTicket.spotNumber]!!
        spotNumberToBookedParkingSpotMap.remove(parkingTicket.spotNumber)
        vehicleParkingSpot.unPark(bookedParkingSpot)
    }

    private fun getVehicleParkingSpot(vehicleType: Vehicle) =
        (vehicleParkingSpots.firstOrNull { it.isVehicleSupported(vehicleType) } ?: throw VehicleNotSupportedException())
}