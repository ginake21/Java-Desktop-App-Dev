/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cejv569.Application;
import com.cejv569.Data.SurgicalData;
import com.cejv569.Data.MedicationData;
import com.cejv569.Data.InpatientData;
import com.cejv569.Data.PatientData;
import com.cejv569.Business.HospitalImpl;
import java.sql.*;
import java.util.*;

/**
 *
 * @author gina0
 */
public class HospitalConsole {
    private HospitalImpl hospitalImpl;
    
    public HospitalConsole(){
        super();
        hospitalImpl = new HospitalImpl();
    }
    
    private void perform(){
        String data; 
        try{
            data = hospitalImpl.findAll().toString();
        }catch(SQLException e){
            data = "Error loading data";
        }
        System.out.println(data);
    }
    
    public static void main(String[] args){
        
        HospitalConsole hospital = new HospitalConsole();
        try{
        hospital.hospitalImpl.createPatient(new PatientData("Hello", "Ko", "Depression", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0")));
        hospital.hospitalImpl.createInpatient(new InpatientData(Timestamp.valueOf("2007-08-23 09:10:10.0"), "12", 60.0, 12.5, 4.3, 4));
        hospital.hospitalImpl.createInpatient(new InpatientData(3));
        hospital.hospitalImpl.createSurgical(new SurgicalData(Timestamp.valueOf("2007-08-23 09:10:10"), "Mrs K", 22.2, 100, 76, 4));
        hospital.hospitalImpl.createSurgical(new SurgicalData(3));
        hospital.hospitalImpl.createMedication(new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "peni", 20, 5, 5));
        hospital.hospitalImpl.findByPatientID(3);
        hospital.hospitalImpl.findByLN("Kent");
        hospital.hospitalImpl.findAll();
        hospital.hospitalImpl.Update(new PatientData("Hello", "Ko", "Anxiety", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0")),2 );
        hospital.hospitalImpl.Update(new InpatientData(Timestamp.valueOf("2011-01-23 09:10:10.0"), "12", 60.0, 12.5, 4.3, 4), 21);
        hospital.hospitalImpl.Update(new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "peni", 20, 5, 5), 1);
        hospital.hospitalImpl.Update(new SurgicalData(Timestamp.valueOf("2000-08-23 09:10:10"), "Mrs G", 92.2, 100, 76, 4), 1);
        hospital.hospitalImpl.deleteInpatient(2);
        hospital.hospitalImpl.deleteMedication(1);
        hospital.hospitalImpl.deleteSurgical(1);
        hospital.hospitalImpl.deletePatient(2);

//            hospital.hospitalImpl.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
//        hospital.perform();
//        System.exit(0);
    }    
}
