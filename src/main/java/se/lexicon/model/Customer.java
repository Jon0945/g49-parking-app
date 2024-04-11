package se.lexicon.model;

import se.lexicon.data.sequencer.CustomerSequencer;

public class Customer {

    private int id;
    private String name;
    private String phoneNumber;
    private Reservation reservation;

    public Customer(String name, String phoneNumber) {
        id = CustomerSequencer.nextId();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer: ").append(id)
                .append(", Name: ").append(name)
                .append(", PhoneNumber: ").append(phoneNumber);
        if (reservation != null) {
            builder.append(", Reservation: YES");
        } else {
            builder.append(", Reservation: NO");
        }


        return builder.toString();
    }

}
