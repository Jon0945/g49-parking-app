package se.lexicon.data.impl;

import se.lexicon.data.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("Reservation data is null");
        Optional<Reservation> optionalReservation = find(reservation.getId());
        if (optionalReservation.isPresent()) throw new IllegalArgumentException("Reservation already exists");
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation r : reservations) {
            if (r.getId().equals(id)) {
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
        for (Reservation r : reservations) {
            if (r.getId().equals(id)) {
                reservations.remove(r);
                return true;
            }
        }
        return false;
    }

    @Override
    public Reservation findByCustomerId(int customerId) {
        for (Reservation r : reservations) {
            if (r.getCustomer().getId() == customerId) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        for (Reservation r : reservations) {
            if (r.getAssociatedVehicle().getLicensePlate().equals(licensePlate)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        for (Reservation r : reservations) {
            if (r.getParkingSpot().getSpotNumber() == spotNumber) {
                return r;
            }
        }
        return null;
    }
}
