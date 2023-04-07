package com.example.domain

import com.example.exception.VehicleNotSupportedException
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.floor

class StadiumParkingFeesCalculator : FeesCalculator {
    override fun calculate(vehicle: Vehicle, bookingStartTime: Instant, bookingEndTime: Instant): Int {
        val numOfMinutesParked = ChronoUnit.MINUTES.between(bookingStartTime, bookingEndTime)
        val numOfHours = (floor(numOfMinutesParked / 60.0)).toInt()


        return when (vehicle) {
            Vehicle.SCOOTER, Vehicle.MOTORCYCLE -> {
                when (numOfHours) {
                    in 0 until 4 -> 30
                    in 4 until 12 -> 30 + 60
                    else -> 30 + 60 + ((numOfHours - 11) * 100)
                }
            }

            Vehicle.CAR, Vehicle.SUV -> {
                when (numOfHours) {
                    in 0 until 4 -> 60
                    in 4 until 12 -> 60 + 120
                    else -> 60 + 120 + ((numOfHours - 11) * 200)
                }
            }

            else -> throw VehicleNotSupportedException()
        }
    }
}