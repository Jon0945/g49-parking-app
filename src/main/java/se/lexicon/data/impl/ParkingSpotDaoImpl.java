package se.lexicon.data.impl;


import se.lexicon.data.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {
    private List<ParkingSpot> spots = new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot){
        if(parkingSpot == null) throw new IllegalArgumentException("Parking Spot data is null");
        Optional<ParkingSpot> optionalParkingSpot = find(parkingSpot.getSpotNumber());
        if(optionalParkingSpot.isPresent()) throw new IllegalArgumentException("Parking Spot already exists");
        spots.add(parkingSpot);
        return parkingSpot;
    }
    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for(ParkingSpot spot : spots) {
            if(spot.getSpotNumber() == spotNumber) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }
    @Override
    public boolean remove(int spotNumber) {
        for(ParkingSpot p : spots) {
            if(p.getSpotNumber() == spotNumber) {
                spots.remove(p);
                return true;
            }}
        return false;
    }
    @Override
    public List<ParkingSpot> findAll() {
        return spots;
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> result = new ArrayList<>();
        for(ParkingSpot p : spots) {
            if(p.getAreaCode() == areaCode) {
                result.add(p);
            }
        }
        return result;
    }
    @Override
    public void occupyParkingSpot(int spotNumber) {
        for(ParkingSpot p : spots) {
            if(p.getSpotNumber() == spotNumber && p.isOccupied()) {
                throw new IllegalArgumentException("Spot is already occupied");
            } else if (p.getSpotNumber() == spotNumber && !p.isOccupied()) {
                p.occupy();
                break;
            } else {
                throw new IllegalArgumentException("Spot number not found");
            }
        }
    }

    @Override
    public void vacateParkingSpot(int spotNumber) {
        for(ParkingSpot p : spots) {
            if(p.getSpotNumber() == spotNumber && !p.isOccupied()) {
                throw new IllegalArgumentException("Spot is not occupied");
            } else if(p.getSpotNumber() == spotNumber && p.isOccupied()) {
                p.vacate();
                break;
            } else {
                throw new IllegalArgumentException("Spot number not found");
            }
        }
    }





}
