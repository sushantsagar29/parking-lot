package com.example.model

import com.example.domain.Vehicle
import java.time.Instant

data class ParkingTicket(
    val ticketNumber: Int,
    val spotNumber: Int,
    val vehicle: Vehicle,
    var bookingStartTime: Instant = Instant.now()

)