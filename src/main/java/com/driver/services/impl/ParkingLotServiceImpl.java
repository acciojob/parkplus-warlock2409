package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import com.driver.transformer.ParkingLotTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot newParkingLot = ParkingLotTransformer.buildParkingLot(name,address);

        ParkingLot responseParkingLot = parkingLotRepository1.save(newParkingLot);

        return ParkingLotTransformer.parkingLotToResponseDto(responseParkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
//        Spot
        Spot spot= ParkingLotTransformer.buildSpot(parkingLotId,numberOfWheels,pricePerHour);
//        parkingLot
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository1.findById(parkingLotId);
        ParkingLot parkingLot = parkingLotOptional.get();
//one to many
        spot.setParkingLot(parkingLot);
        parkingLot.getSpotList().add(spot);
//save parent
        ParkingLot responseLot= parkingLotRepository1.save(parkingLot); // save both parking and spot

        Spot spot1=responseLot.getSpotList().get(responseLot.getSpotList().size()-1);

        return ParkingLotTransformer.spotToResponseSpotDto(spot1);
    }

    @Override
    public void deleteSpot(int spotId) {
//        Optional<Spot> spotOptional = spotRepository1.findById(spotId);
//        Spot spot=spotOptional.get();
//
//        Optional<ParkingLot> parkingLotOptional = parkingLotRepository1.findById(spot.getParkingLot().getId());
//
//        ParkingLot parkingLot = parkingLotOptional.orElseThrow(()-> new NoSuchElementException("spot not found"));
//        parkingLot.getSpotList().remove(spot);
//
//        parkingLotRepository1.save(parkingLot);
        spotRepository1.deleteById(spotId);


    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour)  {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository1.findById(parkingLotId);
        ParkingLot parkingLot = parkingLotOptional.orElseThrow(()-> new IllegalArgumentException("ParkingLot not found"));

        Spot spot=null;

        for(Spot s : parkingLot.getSpotList()){
            if(s.getId() == spotId){
                s.setPricePerHour(pricePerHour);
                spot=spotRepository1.save(s);
                break;
            }
        }
        if(spot != null){
//            spot.setPricePerHour(pricePerHour);
            parkingLotRepository1.save(parkingLot);
            return spot;
        }else{
            throw new IllegalArgumentException("Spot not found in the parking lot");
        }

    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository1.findById(parkingLotId);
        ParkingLot parkingLot = parkingLotOptional.orElseThrow(()-> new NoSuchElementException("Parking Not Found"));

        parkingLotRepository1.deleteById(parkingLotId);
    }
}
