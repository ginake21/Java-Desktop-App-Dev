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

public class PatientData {
    private int patientID;
    private String lastName;
    private String firstName;
    private String diagnosis;
    private Timestamp admissionDate;
    private Timestamp releaseDate;
    
    //non default constructor
    public PatientData(String lastName, String firstName, String diagnosis, Timestamp admissionDate, 
    Timestamp releaseDate){
        super();
//        this.patientID = patientID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.releaseDate = releaseDate;
    }
    
    //default constructor
    public PatientData(){
        super();
//        this.patientID = -1;
        this.lastName = "";
        this.firstName = "";
        this.diagnosis = "";
        this.admissionDate = null;
        this.releaseDate = null;
    }
    
    //getters & setters
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Timestamp getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Timestamp admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "PatientData{" + "patientID=" + patientID + ", lastName=" + lastName 
                + ", firstName=" + firstName + ", diagnosis=" + diagnosis + ", admissionDate=" + admissionDate 
                + ", releaseDate=" + releaseDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.patientID;
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.diagnosis);
        hash = 89 * hash + Objects.hashCode(this.admissionDate);
        hash = 89 * hash + Objects.hashCode(this.releaseDate);
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
        final PatientData other = (PatientData) obj;
        if (this.patientID != other.patientID) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.diagnosis, other.diagnosis)) {
            return false;
        }
        if (!Objects.equals(this.admissionDate, other.admissionDate)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }    
}
