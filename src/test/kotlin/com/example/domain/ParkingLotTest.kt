package com.example.domain

import com.example.exception.NoSpaceAvailableException
import com.example.exception.VehicleNotSupportedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.Stack


class ParkingLotTest : DescribeSpec({
    lateinit var parkingLot: ParkingLot

    describe("test mall parking lot") {
        fun create2TwoWheelerWheelerParkingSpot(): TwoWheelerParkingSpot {

            val parkingSpot1 = ParkingSpot(1, 0)
            val parkingSpot2 = ParkingSpot(2, 0)

            val vacantParkingSpots = Stack<ParkingSpot>()
            vacantParkingSpots.addAll(listOf(parkingSpot1, parkingSpot2))
            return TwoWheelerParkingSpot(vacantParkingSpots)
        }

        beforeTest {
            val twoWheelerParkingSpot = create2TwoWheelerWheelerParkingSpot()
            parkingLot = ParkingLot(
                vehicleParkingSpots = listOf(twoWheelerParkingSpot),
                spotNumberToBookedParkingSpotMap = hashMapOf(),
                feesCalculator = MallParkingFeesCalculator()
            )
        }

        it("should park 2 twoWheelers and then throw error for 3rd one"){
            val motorCycleParkingTicket = parkingLot.park(Vehicle.MOTORCYCLE)
            motorCycleParkingTicket.ticketNumber shouldBe 1
            motorCycleParkingTicket.spotNumber shouldBe 2

            val scooterParkingTicket = parkingLot.park(Vehicle.SCOOTER)
            scooterParkingTicket.ticketNumber shouldBe 2
            scooterParkingTicket.spotNumber shouldBe 1

            val exception =
                shouldThrow<NoSpaceAvailableException> { parkingLot.park(Vehicle.SCOOTER) }

            exception.message shouldBe "No space available"

            val motorCycleParkingReceipt = parkingLot.unPark(scooterParkingTicket)
            motorCycleParkingReceipt.receiptNumber shouldBe 1
            motorCycleParkingReceipt.bookingStartTime shouldBe scooterParkingTicket.bookingStartTime

            val newMotorCycleParkingTicket = parkingLot.park(Vehicle.MOTORCYCLE)
            newMotorCycleParkingTicket.ticketNumber shouldBe 3
            newMotorCycleParkingTicket.spotNumber shouldBe 1
        }

        it("should throw error when non supported vehicle is tried to park"){
            val exception =
                shouldThrow<VehicleNotSupportedException> { parkingLot.park(Vehicle.CAR) }

            exception.message shouldBe "Vehicle not supported"

        }

    }
})