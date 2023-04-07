package com.example.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

import java.time.Instant
import java.time.temporal.ChronoUnit

class MallParkingFeesCalculatorTest : DescribeSpec({

    lateinit var calculator: MallParkingFeesCalculator

    beforeTest {
        calculator = MallParkingFeesCalculator()
    }

    describe("test mall parking fees calculator") {
        it("should get correct fees for motorcycle") {
            val startTime = Instant.now().minus(190, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val motorCycleFee = calculator.calculate(Vehicle.MOTORCYCLE, startTime, endTime)
            motorCycleFee shouldBe 40
        }

        it("should get correct fees for car") {
            val startTime = Instant.now().minus(361, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val carFee = calculator.calculate(Vehicle.CAR, startTime, endTime)
            carFee shouldBe 140
        }

        it("should get correct fees for truck") {
            val startTime = Instant.now().minus(119, ChronoUnit.MINUTES)
            val endTime = Instant.now()

            val truckFee = calculator.calculate(Vehicle.TRUCK, startTime, endTime)
            truckFee shouldBe  100
        }
    }
})