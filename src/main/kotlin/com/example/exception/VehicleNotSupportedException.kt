package com.example.exception

import com.example.constants.constants

class VehicleNotSupportedException(
    override val message: String = constants.VEHICLE_NOT_SUPPORTED_MESSAGE
) : RuntimeException(message)