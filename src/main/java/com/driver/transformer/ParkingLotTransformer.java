package com.driver.transformer;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;

public class ParkingLotTransformer {
    public static ParkingLot buildParkingLot(String name, String address){
        return ParkingLot.builder()
                .name(name)
                .address(address)
                .build();
    }

    public  static ParkingLot parkingLotToResponseDto(ParkingLot parkingLot){
        return ParkingLot.builder()
                .id(parkingLot.getId())
                .name(parkingLot.getName())
                .address(parkingLot.getAddress())
                .spotList(parkingLot.getSpotList())
                .build();
    }
    public static Spot buildSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour){
        SpotType spotType;
        int noOfWheels = numberOfWheels.intValue();

        if(noOfWheels == 2 || noOfWheels < 2){
            spotType = SpotType.TWO_WHEELER;
        } else if (noOfWheels == 4 || noOfWheels < 4) {
            spotType=SpotType.FOUR_WHEELER;
        }else{
            spotType=SpotType.OTHERS;
        }

        return Spot.builder()
                .occupied(false)
                .pricePerHour(noOfWheels)
                .spotType(spotType)
                .build();

    }

    public static Spot spotToResponseSpotDto(Spot spot1) {
        return Spot.builder()
                .id(spot1.getId())
                .occupied(spot1.getOccupied())
                .reservationList(spot1.getReservationList())
                .parkingLot(spot1.getParkingLot())
                .spotType(spot1.getSpotType())
                .pricePerHour(spot1.getPricePerHour())
                .build();
    }
}
