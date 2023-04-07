package com.example.domain

import com.example.exception.NoSpaceAvailableException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.Stack

class FourWheelerParkingSpotTest : DescribeSpec({

    val parkingSpot1 = ParkingSpot(1, 0)
    val parkingSpot2 = ParkingSpot(2, 0)

    lateinit var fourWheelerParkingSpot: FourWheelerParkingSpot

    beforeTest {
        val vacantParkingSpots = Stack<ParkingSpot>()
        vacantParkingSpots.addAll(listOf(parkingSpot1, parkingSpot2))

        fourWheelerParkingSpot = FourWheelerParkingSpot(vacantParkingSpots)
    }

    describe("test vehicle parking slot") {
        it("should park in the parking slot available from LIFO") {
            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 2

            val parkingSpot = fourWheelerParkingSpot.park()

            parkingSpot.spotNumber shouldBe 2
            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 1
        }

        it("should vacate the parking slot when vehicle un-parked") {
            fourWheelerParkingSpot.park()
            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 1

            val parkingSpot = fourWheelerParkingSpot.unPark(parkingSpot2)

            parkingSpot.spotNumber shouldBe 2
            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 2
        }
        it("should throw error when no vacant parking slots are available") {
            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 2
            val firstParkingSpot = fourWheelerParkingSpot.park()
            val secondParkingSpot = fourWheelerParkingSpot.park()
            firstParkingSpot.spotNumber shouldBe 2
            secondParkingSpot.spotNumber shouldBe 1

            fourWheelerParkingSpot.vacantParkingSpots.size shouldBe 0

            val exception =
                shouldThrow<NoSpaceAvailableException> { fourWheelerParkingSpot.park() }

            exception.message shouldBe "No space available"
        }
    }
})