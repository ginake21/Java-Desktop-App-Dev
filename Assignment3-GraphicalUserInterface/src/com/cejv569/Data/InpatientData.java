/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cejv569.Data;

/**
 *
 * @author gina0
 */
import java.sql.Timestamp;
import java.util.Objects;

public class InpatientData {
    private int id;
    private Timestamp dateOfStay;
    private String roomNumber;
    private double dailyRate;
    private double supplies;
    private double services;
    private int patientID;


    // non default constructor
    public InpatientData(Timestamp dateOfStay, String roomNumber, double dailyRate, double supplies, double services, int patientID){
        super();
        this.dateOfStay = dateOfStay;
        this.roomNumber = roomNumber;
        this.dailyRate = dailyRate;
        this.supplies = supplies;
        this.services = services;
        this.patientID = patientID;
    }
    
    // default constructor => this will cause a violation of foreign key constraint, so put at least patientID
    public InpatientData(int patientID){
        super();
//        this.id = -1;
        this.dateOfStay = null;
        this.roomNumber = "";
        this.dailyRate = 0;
        this.supplies = 0;
        this.services = 0;
        this.patientID = patientID;
    }

    // this is used for search by id - the primary key ( not by patientID)
    // String any doesn't have real function, only used for overloading - different signatures
    public InpatientData(int ID, String any){
        super();
        this.dateOfStay = null;
        this.roomNumber = "";
        this.dailyRate = 0;
        this.supplies = 0;
        this.services = 0;
        this.patientID = 0;
    }    
    
    public InpatientData(){
        // this constructor is used for findAllInpatient() 
    }
    // setters & getters
    public int getId() {
        return id;
    }
// it's generated automatically by the system, so we don't need it 
    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateOfStay() {
        return dateOfStay;
    }

    public void setDateOfStay(Timestamp dateOfStay) {
        this.dateOfStay = dateOfStay;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getSupplies() {
        return supplies;
    }

    public void setSupplies(double supplies) {
        this.supplies = supplies;
    }

    public double getServices() {
        return services;
    }

    public void setServices(double services) {
        this.services = services;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "InpatientData{" + "id=" + id + ", dateOfStay=" + dateOfStay + ", roomNumber=" + roomNumber 
                + ", dailyRate=" + dailyRate + ", supplies=" + supplies + ", services=" + services + ", patientID=" + patientID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.dateOfStay);
        hash = 89 * hash + Objects.hashCode(this.roomNumber);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.dailyRate) ^ (Double.doubleToLongBits(this.dailyRate) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.supplies) ^ (Double.doubleToLongBits(this.supplies) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.services) ^ (Double.doubleToLongBits(this.services) >>> 32));
        hash = 89 * hash + this.patientID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InpatientData other = (InpatientData) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.dailyRate) != Double.doubleToLongBits(other.dailyRate)) {
            return false;
        }
        if (Double.doubleToLongBits(this.supplies) != Double.doubleToLongBits(other.supplies)) {
            return false;
        }
        if (Double.doubleToLongBits(this.services) != Double.doubleToLongBits(other.services)) {
            return false;
        }
        if (this.patientID != other.patientID) {
            return false;
        }
        if (!Objects.equals(this.roomNumber, other.roomNumber)) {
            return false;
        }
        if (!Objects.equals(this.dateOfStay, other.dateOfStay)) {
            return false;
        }
        return true;
    }
    
    
    
}