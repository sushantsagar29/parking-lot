package com.example.domain

import com.example.exception.VehicleNotSupportedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.temporal.ChronoUnit


class AirportParkingFeesCalculatorTest : DescribeSpec({
    lateinit var calculator: AirportParkingFeesCalculator

    beforeTest {
        calculator = AirportParkingFeesCalculator()
    }

    describe("test airport parking fees calculator") {
        it("should get correct fees for motorcycle") {
            val startTime = Instant.now().minus(55, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 0
        }

        it("should get correct fees for motorcycle parked for less than a day") {
            val startTime = Instant.now().minus(899, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 60
        }

        it("should get correct fees for motorcycle parked for more than a day") {
            val startTime = Instant.now().minus(36, ChronoUnit.HOURS)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 160
        }

        it("should get correct fees for car") {
            val startTime = Instant.now().minus(50, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val carFee = calculator.calculate(Vehicle.CAR, startTime, endTime)
            carFee shouldBe 60
        }

        it("should get correct fees for suv parked for less than a day") {
            val startTime = Instant.now().minus(1439, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val suvFee = calculator.calculate(Vehicle.SUV, startTime, endTime)
            suvFee shouldBe 80
        }

        it("should get correct fees for car parked for more than a day") {
            val startTime = Instant.now().minus(73, ChronoUnit.HOURS)
            val endTime = Instant.now()

            val carFee = calculator.calculate(Vehicle.CAR, startTime, endTime)
            carFee shouldBe 400
        }

        it("should throw error for non supported vehicle type") {
            val startTime = Instant.now().minus(785, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val exception =
                shouldThrow<VehicleNotSupportedException> { calculator.calculate(Vehicle.BUS, startTime, endTime) }

            exception.message shouldBe "Vehicle not supported"
        }
    }
})