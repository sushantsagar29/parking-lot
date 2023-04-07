package com.example.model

import java.time.Instant

data class ParkingReceipt(
    val receiptNumber: Int,
    val bookingStartTime: Instant,
    val bookingEndTime: Instant,
    val fees: Int
)