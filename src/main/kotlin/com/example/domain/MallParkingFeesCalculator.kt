package com.example.domain

import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.ceil

class MallParkingFeesCalculator : FeesCalculator {
    override fun calculate(vehicle: Vehicle, bookingStartTime: Instant, bookingEndTime: Instant): Int {
        val numOfMinutesParked = ChronoUnit.MINUTES.between(bookingStartTime, bookingEndTime)
        val numOfHours = (ceil(numOfMinutesParked / 60.0)).toInt()

        return when (vehicle) {
            Vehicle.SCOOTER, Vehicle.MOTORCYCLE -> {
                numOfHours * 10
            }

            Vehicle.CAR, Vehicle.SUV -> {
                numOfHours * 20
            }

            Vehicle.BUS, Vehicle.TRUCK -> {
                numOfHours * 50
            }
        }
    }
}