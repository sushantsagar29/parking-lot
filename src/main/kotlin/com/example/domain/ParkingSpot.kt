package com.example.domain

import java.time.Instant

data class ParkingSpot(
    val spotNumber: Int,
    val floorNum: Int = 0,
    var bookingStartTime: Instant? = null
)