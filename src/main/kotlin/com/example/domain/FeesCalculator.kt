package com.example.domain

import java.time.Instant

interface FeesCalculator {
    fun calculate(vehicle: Vehicle, bookingStartTime: Instant, bookingEndTime: Instant): Int
}
