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

public class MedicationData {
    private int id;
    private Timestamp dateOfMed;
    private String med;
    private double unitCost;
    private double units;
    private int patientID;
    
    // non default constructor
    public MedicationData(Timestamp dateOfMed, String med, double unitCost, double units, int patientID){
//        this.id = id;
        this.dateOfMed = dateOfMed;
        this.med = med;
        this.unitCost = unitCost;
        this.units = units;
        this.patientID = patientID;
    }
    
    // default constructor won't work, as patientID is a constraint foreign key and must be there 
    public MedicationData(int patientID){
//        this.id = -1;
        this.dateOfMed = null;
        this.med = "";
        this.unitCost = 0;
        this.units = 0;
        this.patientID = patientID;
    }
    
    //getters & setters
    public int getId() {
        return id;
    }

// it's generated automatically by the system, so we don't need it     
    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateOfMed() {
        return dateOfMed;
    }

    public void setDateOfMed(Timestamp dateOfMed) {
        this.dateOfMed = dateOfMed;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "MedicationData{" + "id=" + id + ", dateOfMed=" + dateOfMed + ", med=" + med 
                + ", unitCost=" + unitCost + ", units=" + units + ", patientID=" + patientID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.dateOfMed);
        hash = 23 * hash + Objects.hashCode(this.med);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.unitCost) ^ (Double.doubleToLongBits(this.unitCost) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.units) ^ (Double.doubleToLongBits(this.units) >>> 32));
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
        final MedicationData other = (MedicationData) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.unitCost) != Double.doubleToLongBits(other.unitCost)) {
            return false;
        }
        if (Double.doubleToLongBits(this.units) != Double.doubleToLongBits(other.units)) {
            return false;
        }
        if (this.patientID != other.patientID) {
            return false;
        }
        if (!Objects.equals(this.med, other.med)) {
            return false;
        }
        if (!Objects.equals(this.dateOfMed, other.dateOfMed)) {
            return false;
        }
        return true;
    }
    
    
}