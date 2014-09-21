package com.epam.travel.model;

/**
 * @author Andrei Sauchanka
 */
public class Trip {

    private long tripDate;

    private String tripDateStr;

    private Location fromLocation;

    private Location toLocation;

    private Contact contact;

    private int daysLeft;

    private Boolean hidden;

    public long getTripDate() {
        return tripDate;
    }

    public void setTripDate(long tripDate) {
        this.tripDate = tripDate;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getTripDateStr() {
        return tripDateStr;
    }

    public void setTripDateStr(String tripDateStr) {
        this.tripDateStr = tripDateStr;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
