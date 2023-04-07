package com.example.domain

import com.example.exception.VehicleNotSupportedException
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.ceil
import kotlin.math.floor

class AirportParkingFeesCalculator : FeesCalculator {
    override fun calculate(vehicle: Vehicle, bookingStartTime: Instant, bookingEndTime: Instant): Int {
        val numOfMinutesParked = ChronoUnit.MINUTES.between(bookingStartTime, bookingEndTime)
        val numOfHours = (floor(numOfMinutesParked / 60.0)).toInt()


        return when (vehicle) {
            Vehicle.SCOOTER, Vehicle.MOTORCYCLE -> {
                when (numOfHours) {
                    in 0 until 1 -> 0
                    in 1 until 8 -> 40
                    in 8 until 24 -> 60
                    else -> ceil(numOfHours / 24.0).toInt() * 80
                }
            }

            Vehicle.CAR, Vehicle.SUV -> {
                when (numOfHours) {
                    in 0 until 12 -> 60
                    in 12 until 24 -> 80
                    else -> ceil(numOfHours / 24.0).toInt() * 100
                }
            }

            else -> throw VehicleNotSupportedException()
        }
    }
}