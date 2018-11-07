/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cejv569.Interfaces;

/**
 *
 * @author gina0
 */
import com.cejv569.Data.PatientData;
import com.cejv569.Data.InpatientData;
import com.cejv569.Data.SurgicalData;
import com.cejv569.Data.MedicationData;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Hospital { 
    //Create - create a patient
    public int createPatient(PatientData patientData) throws SQLException;
    //Create - create an inpatient, a surgical, a medication *the above records must be connected to a master record

    public int createInpatient(InpatientData inPatientData) throws SQLException;
    public int createSurgical(SurgicalData surgicalData) throws SQLException;
    public int createMedication(MedicationData medicationData) throws SQLException;
    
    //Read - find Patient records by ID *when a master record is found, then all of its detail records must also be retrieved
//    public ArrayList<Object> findByPatientID(int patientID) throws SQLException;
    public PatientData findByPatientID(int patientID)throws SQLException;
    public ArrayList<InpatientData> findByPatientID_I(int patientID)throws SQLException;
    public ArrayList<MedicationData> findByPatientID_M(int patientID)throws SQLException;
    public ArrayList<SurgicalData> findByPatientID_S(int patientID)throws SQLException;    
    
    
    //Read - find Patient records by LastName *when a master record is found, then all of its detail records must also be retrieved
    public ArrayList<PatientData> findByLN(String lastName) throws SQLException;
    
    //Read - find all Patient records
    public ArrayList<PatientData> findAll() throws SQLException;
    
    //Update - update a master record
    public int Update(PatientData patientData, int patientid) throws SQLException;
    //Update - update a detail record

    public int Update(InpatientData inpatientData, int inpatientID) throws SQLException;
    public int Update(MedicationData medicationData, int medicationID) throws SQLException;
    public int Update(SurgicalData surgicalData, int surgicalID) throws SQLException;
    
    //Delete - delete a detial record
    public int deleteInpatient(int id) throws SQLException;
    public int deleteMedication(int id) throws SQLException;
    public int deleteSurgical(int id) throws SQLException;
    //Delete - delete a master record
    public int deletePatient(int id) throws SQLException;
}
