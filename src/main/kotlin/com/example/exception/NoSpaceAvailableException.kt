package com.example.exception

import com.example.constants.constants

class NoSpaceAvailableException(
    override val message: String = constants.NO_SPACE_AVAILABLE_MESSAGE
) : RuntimeException(message)