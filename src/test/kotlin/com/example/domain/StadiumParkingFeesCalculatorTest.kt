package com.example.domain

import com.example.exception.VehicleNotSupportedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.temporal.ChronoUnit


class StadiumParkingFeesCalculatorTest : DescribeSpec({
    lateinit var calculator: StadiumParkingFeesCalculator

    beforeTest {
        calculator = StadiumParkingFeesCalculator()
    }

    describe("test stadium parking fees calculator") {
        it("should get correct fees for motorcycle") {
            val startTime = Instant.now().minus(220, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 30
        }

        it("should get correct fees for motorcycle parked for less than a day") {
            val startTime = Instant.now().minus(899, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 390
        }


        it("should get correct fees for car") {
            val startTime = Instant.now().minus(690, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val carFee = calculator.calculate(Vehicle.CAR, startTime, endTime)
            carFee shouldBe 180
        }

        it("should get correct fees for suv parked for less than a day") {
            val startTime = Instant.now().minus(785, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val suvFee = calculator.calculate(Vehicle.SUV, startTime, endTime)
            suvFee shouldBe 580
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