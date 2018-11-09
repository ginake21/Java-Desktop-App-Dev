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

public class SurgicalData {
    private int id;
    private Timestamp dateOfSurgery;
    private String surgery;
    private double roomFee;
    private double surgeonFee;
    private double supplies;
    private int patientID;
    
    // non default constructor
    public SurgicalData(Timestamp dateOfSurgery, String surgery, double roomFee, double surgeonFee, double supplies, int patientID){
//        this.id = id;
        this.dateOfSurgery = dateOfSurgery;
        this.surgery = surgery;
        this.roomFee = roomFee;
        this.surgeonFee = surgeonFee;
        this.supplies = supplies;
        this.patientID = patientID;
    }
    
//     default constructor => this will cause a violation of foreign key constraint, so must inlude patientID when instantiating
    public SurgicalData(int patientID){
//        this.id = -1;
        this.dateOfSurgery = null;
        this.surgery = "";
        this.roomFee = 0;
        this.surgeonFee = 0;
        this.supplies = 0;
        this.patientID = patientID;
    }

    public SurgicalData(){}
    // getters & setters
    public int getId() {
        return id;
    }
// it's generated automatically by the system, so we don't need it 
    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateOfSurgery() {
        return dateOfSurgery;
    }

    public void setDateOfSurgery(Timestamp dateOfSurgery) {
        this.dateOfSurgery = dateOfSurgery;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public double getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(double roomFee) {
        this.roomFee = roomFee;
    }

    public double getSurgeonFee() {
        return surgeonFee;
    }

    public void setSurgeonFee(double surgeonFee) {
        this.surgeonFee = surgeonFee;
    }

    public double getSupplies() {
        return supplies;
    }

    public void setSupplies(double supplies) {
        this.supplies = supplies;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "SurgicalData{" + "id=" + id + ", dateOfSurgery=" + dateOfSurgery + ", surgery=" + surgery + ", roomFee=" + roomFee + ", surgeonFee=" + surgeonFee + ", supplies=" + supplies + ", patientID=" + patientID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.dateOfSurgery);
        hash = 23 * hash + Objects.hashCode(this.surgery);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.roomFee) ^ (Double.doubleToLongBits(this.roomFee) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.surgeonFee) ^ (Double.doubleToLongBits(this.surgeonFee) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.supplies) ^ (Double.doubleToLongBits(this.supplies) >>> 32));
        hash = 23 * hash + this.patientID;
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
        final SurgicalData other = (SurgicalData) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.roomFee) != Double.doubleToLongBits(other.roomFee)) {
            return false;
        }
        if (Double.doubleToLongBits(this.surgeonFee) != Double.doubleToLongBits(other.surgeonFee)) {
            return false;
        }
        if (Double.doubleToLongBits(this.supplies) != Double.doubleToLongBits(other.supplies)) {
            return false;
        }
        if (this.patientID != other.patientID) {
            return false;
        }
        if (!Objects.equals(this.surgery, other.surgery)) {
            return false;
        }
        if (!Objects.equals(this.dateOfSurgery, other.dateOfSurgery)) {
            return false;
        }
        return true;
    }
    
    
}
