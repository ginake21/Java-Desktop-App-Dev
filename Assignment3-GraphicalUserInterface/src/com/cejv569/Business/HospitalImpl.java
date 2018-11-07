/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cejv569.Business;

import com.cejv569.Data.MedicationData;
import com.cejv569.Data.SurgicalData;
import com.cejv569.Data.PatientData;
import com.cejv569.Data.InpatientData;
import com.cejv569.Interfaces.Hospital;
import java.sql.*;
import java.util.*;
import java.sql.CallableStatement;


/**
 *
 * @author gina0
 */
public class HospitalImpl implements Hospital {
    String url = "jdbc:derby://localhost:1527/hospitalDB";
    String user = "hospital";
    String password = "hospital";
    
    public HospitalImpl(){
        super();
    }
    
    @Override
//    checked
    public int createPatient(PatientData patientData) throws SQLException{
        int result;
        String query = "INSERT INTO PATIENT(lastName, firstName, diagnosis, admissionDate, releaseDate) VALUES(?, ?, ? ,? , ?)";
        //connection
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, patientData.getLastName());
            ps.setString(2, patientData.getFirstName());
            ps.setString(3, patientData.getDiagnosis());
            ps.setTimestamp(4, patientData.getAdmissionDate());
            ps.setTimestamp(5, patientData.getReleaseDate());
            
            result = ps.executeUpdate();
        }
        return result;
    }

    @Override
//    checked
    public int createInpatient(InpatientData inPatientData) throws SQLException{
        int result;
        String query = "INSERT INTO INPATIENT(DATEOFSTAY, ROOMNUMBER, DAILYRATE, SUPPLIES, SERVICES, PATIENTID) VALUES(?, ?, ?, ?, ? ,?)";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setTimestamp(1, inPatientData.getDateOfStay());
            ps.setString(2, inPatientData.getRoomNumber());
            ps.setDouble(3, inPatientData.getDailyRate());
            ps.setDouble(4, inPatientData.getSupplies());
            ps.setDouble(5, inPatientData.getServices());
            ps.setInt(6, inPatientData.getPatientID());
            
            result = ps.executeUpdate();          
        }
        return result;
    }

    @Override
//    checked
    public int createSurgical(SurgicalData surgicalData) throws SQLException{
        int result;
        String query = "INSERT INTO SURGICAL(DATEOFSURGERY, SURGERY, ROOMFEE, SURGEONFEE, SUPPLIES, PATIENTID) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setTimestamp(1, surgicalData.getDateOfSurgery());
            ps.setString(2, surgicalData.getSurgery());
            ps.setDouble(3, surgicalData.getRoomFee());
            ps.setDouble(4, surgicalData.getSurgeonFee());
            ps.setDouble(5, surgicalData.getSupplies());
            ps.setInt(6, surgicalData.getPatientID());
            
            result = ps.executeUpdate();        
        }
        return result;
    }
    
    @Override
//    checked
    public int createMedication(MedicationData medicationData) throws SQLException{
        int result;
        String query = "INSERT INTO MEDICATION(DATEOFMED, MED, UNITCOST, UNITS, PATIENTID) VALUES(?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
                ps.setTimestamp(1, medicationData.getDateOfMed());
                ps.setString(2, medicationData.getMed());
                ps.setDouble(3, medicationData.getUnitCost());
                ps.setDouble(4, medicationData.getUnits());
                ps.setInt(5, medicationData.getPatientID());
                
                result = ps.executeUpdate();
        }
        return result;
    }

    @Override
//    checked
    public PatientData findByPatientID(int patientID) throws SQLException{
        PatientData patientData = new PatientData();
        String query = "Select LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE FROM PATIENT WHERE patientID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, patientID);
            try(ResultSet resultSet = ps.executeQuery()){
                // if resultSet is not ampty
                if(resultSet.next()){
                    // DATA FOR PATIENT
                    patientData = new PatientData();
                    patientData.setPatientID(patientID);
                    patientData.setLastName(resultSet.getString("LASTNAME"));
                    patientData.setFirstName(resultSet.getString("FIRSTNAME"));
                    patientData.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                    patientData.setAdmissionDate(resultSet.getTimestamp("ADMISSIONDATE"));
                    patientData.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));
                }
            }
        }
        System.out.println(patientData.toString());
        findByPatientID_I(patientID);
        findByPatientID_M(patientID);
        findByPatientID_S(patientID);
        
        return patientData;
    }
    
    @Override
//    checked
    public ArrayList<InpatientData> findByPatientID_I(int patientID)throws SQLException{
        ArrayList<InpatientData> inpatientArray = new ArrayList<>();
        String query = "SELECT ID, DATEOFSTAY, ROOMNUMBER, DAILYRATE, SUPPLIES, SERVICES FROM INPATIENT WHERE PATIENTID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query);)
        {
            ps.setInt(1, patientID);
            try(ResultSet resultSet = ps.executeQuery()){                
                while(resultSet.next()){                    
                    InpatientData inpatientData = new InpatientData(patientID);                 
                    inpatientData.setId(resultSet.getInt("ID"));
                    inpatientData.setDateOfStay(resultSet.getTimestamp("DATEOFSTAY"));
                    inpatientData.setRoomNumber(resultSet.getString("ROOMNUMBER"));
                    inpatientData.setDailyRate(resultSet.getDouble("DAILYRATE"));
                    inpatientData.setSupplies(resultSet.getDouble("SUPPLIES"));
                    inpatientData.setServices(resultSet.getDouble("SERVICES"));
                    
                    inpatientArray.add(inpatientData);                
                }
            }            
        }
        System.out.println("\nInpatient data for patient ID #" + patientID + ":");
        for(InpatientData i : inpatientArray){
            System.out.println(i.toString());
        } 

        return inpatientArray;
    }
    
    @Override
//    checked
    public ArrayList<MedicationData> findByPatientID_M(int patientID)throws SQLException{
        ArrayList<MedicationData> medicationArray = new ArrayList<MedicationData>();
        String query = "SELECT ID, DATEOFMED, MED, UNITCOST, UNITS, PATIENTID FROM MEDICATION WHERE PATIENTID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query);)
        {
            ps.setInt(1, patientID);
            try(ResultSet resultSet = ps.executeQuery()){
                while(resultSet.next()){
                    MedicationData medicationData = new MedicationData(patientID);                    
                    medicationData.setId(resultSet.getInt("ID"));
                    medicationData.setDateOfMed(resultSet.getTimestamp("DATEOFMED"));
                    medicationData.setMed(resultSet.getString("MED"));
                    medicationData.setUnitCost(resultSet.getDouble("UNITCOST"));
                    medicationData.setUnits(resultSet.getDouble("UNITS"));
                    
                    medicationArray.add(medicationData);
                }
            }            
        }
        System.out.println("\nMedication data for patient ID #" + patientID + ":");
        for(MedicationData m : medicationArray){
            System.out.println(m.toString());
        }
        return medicationArray;
    }
    
    @Override
    public ArrayList<SurgicalData> findByPatientID_S(int patientID)throws SQLException{
        ArrayList<SurgicalData> surgicalArray= new ArrayList<>();
        String query = "SELECT ID, DATEOFSURGERY, SURGERY, ROOMFEE, SURGEONFEE, SUPPLIES FROM SURGICAL WHERE PATIENTID = ? ";
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query)
            ){
            ps.setInt(1, patientID);
            try(ResultSet resultSet = ps.executeQuery()){
                while(resultSet.next()){
                    SurgicalData surgicalData = new SurgicalData(patientID);
                    surgicalData.setId(resultSet.getInt("ID"));
                    surgicalData.setDateOfSurgery(resultSet.getTimestamp("DATEOFSURGERY"));
                    surgicalData.setSurgery(resultSet.getString("SURGERY"));
                    surgicalData.setRoomFee(resultSet.getDouble("ROOMFEE"));
                    surgicalData.setSurgeonFee(resultSet.getDouble("SURGEONFEE"));
                    surgicalData.setSupplies(resultSet.getDouble("SUPPLIES")); 
//                    System.out.println("everything is good");
                    
                    surgicalArray.add(surgicalData);
                }
            }
        }
        System.out.println("\nSurgical data for patient ID #" + patientID + ":");
        for(SurgicalData s : surgicalArray){
            System.out.println(s.toString());
        }

        return surgicalArray;
    }
    
    
    


    @Override
//    checked
    public ArrayList<PatientData> findByLN(String lastName) throws SQLException{
        ArrayList<PatientData> patientArray = new ArrayList<>();
        int count = 0; 
        int time = 0;
        String query = "SELECT PATIENTID, LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE FROM PATIENT WHERE LASTNAME = ?";
        
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setString(1, lastName);
            
            try(ResultSet resultSet = ps.executeQuery()){
                while(resultSet.next()){
                    count++;
                }
                System.out.println("There are " + count + " result(s) found with the last name " + "\"" + lastName + "\"\n");
            }
            try(ResultSet resultSet = ps.executeQuery()){
                while(resultSet.next()){
                    time++;
                    PatientData patientData = new PatientData();                    
                    patientData.setPatientID(resultSet.getInt("PATIENTID"));
                    patientData.setLastName(lastName);
                    patientData.setFirstName(resultSet.getString("FIRSTNAME"));
                    patientData.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                    patientData.setAdmissionDate(resultSet.getTimestamp("ADMISSIONDATE"));
                    patientData.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));

                    patientArray.add(patientData);
                    System.out.println("Result " + time + ": \nPatient data for \"" + patientData.getFirstName() + " " + patientData.getLastName() + "\""
                            + " / Patient ID# " + patientData.getPatientID());
                    int patientID = patientData.getPatientID();
                    System.out.println(patientData);
                    findByPatientID_I(patientID);
                    findByPatientID_M(patientID);
                    findByPatientID_S(patientID);

                    System.out.println("***************************************************************************************************************\n");                    
                }
            }
        }
//        for(PatientData p : patientArray){
//            System.out.println(p.toString());
//        }
        return patientArray;                    
    }
  

    @Override
//    checked
    public ArrayList<PatientData> findAll() throws SQLException{
        ArrayList<PatientData> patientArray = new ArrayList<>();
        String query = "SELECT PATIENTID, LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE FROM PATIENT";
        
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery())
        {
            while(resultSet.next()){
                PatientData patientData = new PatientData();                    
                patientData.setPatientID(resultSet.getInt("PATIENTID"));
                patientData.setLastName(resultSet.getString("LASTNAME"));
                patientData.setFirstName(resultSet.getString("FIRSTNAME"));
                patientData.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                patientData.setAdmissionDate(resultSet.getTimestamp("ADMISSIONDATE"));
                patientData.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));

                patientArray.add(patientData);
            }
            
        }
        for(PatientData p : patientArray){
            System.out.println(p.toString());
        }
        return patientArray;
    }
 
    @Override
//  checked - update a master record
    public int Update(PatientData patientData, int id) throws SQLException{
        int result;
        String query = "UPDATE PATIENT SET LASTNAME = ?, FIRSTNAME = ?, DIAGNOSIS = ?, ADMISSIONDATE = ?, "
                + "RELEASEDATE =? WHERE PATIENTID = ? ";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, patientData.getLastName());
            ps.setString(2, patientData.getFirstName());
            ps.setString(3,patientData.getDiagnosis());
            ps.setTimestamp(4, patientData.getAdmissionDate());
            ps.setTimestamp(5, patientData.getReleaseDate());
//            ps.setInt(6, patientData.getPatientID());
//            can't create a new patientData instance with patientID, which is generated automatically by the system
            ps.setInt(6, id);
            result = ps.executeUpdate();
        }
        return result;
    }
    
    @Override
//    checked
    public int Update(InpatientData inpatientData, int inpatientID) throws SQLException{
        int result;
        String query = "UPDATE INPATIENT SET DATEOFSTAY = ?, ROOMNUMBER = ?, DAILYRATE = ?, SUPPLIES = ?, "
                + "SERVICES = ?, PATIENTID = ? WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setTimestamp(1, inpatientData.getDateOfStay());
            ps.setString(2, inpatientData.getRoomNumber());
            ps.setDouble(3, inpatientData.getDailyRate());
            ps.setDouble(4, inpatientData.getSupplies());
            ps.setDouble(5, inpatientData.getServices());
            ps.setInt(6, inpatientData.getPatientID());
            ps.setInt(7, inpatientID);
            
            
            result = ps.executeUpdate();
        }
        return result;
    }
    
    @Override
//    checked
    public int Update(MedicationData medicationData, int medicationID) throws SQLException{
        int result;
        String query = "UPDATE MEDICATION SET DATEOFMED = ?, MED = ?, UNITCOST = ?, UNITS = ?, PATIENTID = ? WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setTimestamp(1, medicationData.getDateOfMed());
            ps.setString(2, medicationData.getMed());
            ps.setDouble(3, medicationData.getUnitCost());
            ps.setDouble(4, medicationData.getUnits());
            ps.setInt(5, medicationData.getPatientID());
            ps.setInt(6, medicationID);
            
            result = ps.executeUpdate();
        }
        return result;
    }
    
    @Override
//    checked
    public int Update(SurgicalData surgicalData, int surgicalID) throws SQLException{
        int result;
        String query = "UPDATE SURGICAL SET DATEOFSURGERY = ?, SURGERY = ?, ROOMFEE = ?, SURGEONFEE = ?,"
                + " SUPPLIES =?,PATIENTID = ? WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setTimestamp(1, surgicalData.getDateOfSurgery());
            ps.setString(2, surgicalData.getSurgery());
            ps.setDouble(3, surgicalData.getRoomFee());
            ps.setDouble(4, surgicalData.getSurgeonFee());
            ps.setDouble(5, surgicalData.getSupplies());
            ps.setInt(6, surgicalData.getPatientID());
            ps.setInt(7, surgicalID);
            
            
            result = ps.executeUpdate();
        }
        return result;
    }
    
    //delete detail record
    @Override
//    checked
    public int deleteInpatient(int id) throws SQLException{
        int result;
        String query = "DELETE FROM INPATIENT WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            result = ps.executeUpdate();
        }
        return result;
    }
    
    @Override
//    checked
    public int deleteMedication(int id) throws SQLException{
        int result;
        String query = "DELETE FROM MEDICATION WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            result = ps.executeUpdate();
        }
        return result;
    }
    
    @Override
//    checked
    public int deleteSurgical(int id) throws SQLException{
        int result;
        String query = "DELETE FROM SURGICAL WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            result = ps.executeUpdate();
        }
        return result;
    }

    @Override
    //table has ON DELETE CASCADE (if a record in the PATIEJT is deleted, the corresponding records in CHILD will be deleted automatically)
//    checked
    public int deletePatient(int patientID) throws SQLException{
        int result;
        String query = "DELETE FROM PATIENT WHERE PATIENTID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, patientID);
            result = ps.executeUpdate();
        }
        return result;   
    }
    
//methods for testing only
    public int findAll(String type) throws SQLException{
        int count = 0;
        String query = null;
        if(type.equalsIgnoreCase("inpatient")){
            query = "SELECT * FROM INPATIENT";
        } else if(type.equalsIgnoreCase("medication")){
            query = "SELECT * FROM MEDICATION";
        } else if(type.equalsIgnoreCase("surgical")){
            query = "SELECT * FROM SURGICAL";
        } else{
            System.out.println("Error happened! Please try again with inpatient, medication, or surgical");
            System.exit(0);
        }
        
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()){
            while(resultSet.next()){
                count++;
            }
        }
        return count;
    }
     
    
    // extra test for metaData
    
    public void metaColumn() throws SQLException{
        String query = "SELECT LASTNAME, FIRSTNAME, DIAGNOSIS, ADMISSIONDATE, RELEASEDATE, PATIENTID FROM PATIENT";
        try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()){
            if(resultSet.next()){
                PatientData patientData = new PatientData();
                patientData.setLastName(resultSet.getString("LASTNAME"));
                patientData.setLastName(resultSet.getString("FIRSTNAME"));
                patientData.setDiagnosis(resultSet.getString("DIAGNOSIS"));
                patientData.setReleaseDate(resultSet.getTimestamp("RELEASEDATE"));
                patientData.setPatientID(resultSet.getInt("PATIENTID"));      
                
                System.out.println(patientData.toString());
            }

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();             
            System.out.println("There are " + columnCount + " columns");
            //so far so good
            
            ArrayList<String> col = new ArrayList<>();
            if(col.size() == 0){
                System.out.println("So far, nothing inside the col array");
            }
            
            
            for(int i = 1; i <= columnCount; i++){
                // make sure the column start from 1 in SQL file
                String columnName = metaData.getColumnName(i);
                System.out.println(columnName);
                col.add(metaData.getColumnName(i));
            }
            int count = 1;
            for (String e : col){                
                System.out.println("Column " + count + " : " + e);
                count++;
            }
        }
    }
    
    public double runQuery(String query, int patientID)throws SQLException{
        double result = 0;
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, patientID);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    result = rs.getDouble("cost");
                }
            }
        }
        return result;
    }
     
    
    
    public InpatientData findByInpatientID(int inpatientID)throws SQLException{
        InpatientData inpatientData = new InpatientData(inpatientID, "any");
        String query = "SELECT ID, DATEOFSTAY, ROOMNUMBER, DAILYRATE, SUPPLIES, SERVICES, PATIENTID FROM INPATIENT WHERE ID = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query);)
        {
            ps.setInt(1, inpatientID);
            try(ResultSet resultSet = ps.executeQuery()){                
                if(resultSet.next()){                    
               
                    inpatientData.setId(inpatientID);
                    inpatientData.setDateOfStay(resultSet.getTimestamp("DATEOFSTAY"));
                    inpatientData.setRoomNumber(resultSet.getString("ROOMNUMBER"));
                    inpatientData.setDailyRate(resultSet.getDouble("DAILYRATE"));
                    inpatientData.setSupplies(resultSet.getDouble("SUPPLIES"));
                    inpatientData.setServices(resultSet.getDouble("SERVICES"));
                    inpatientData.setPatientID(resultSet.getInt("PATIENTID"));                 
                }  
                else{
                    inpatientData.setId(inpatientID);
                }
            }            
        }
        System.out.println("\nInpatient data for Inpatient ID #" + inpatientID + ":\n" + inpatientData.toString());
        return inpatientData;
    }
    
    public static void main(String[] args) throws Exception{
        HospitalImpl test = new HospitalImpl();
//        test.createPatient(new PatientData("Hello", "Ko", "JAVAobsessed", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0")));
//        test.createInpatient(new InpatientData(Timestamp.valueOf("2007-08-23 09:10:10.0"), "001", 60.0, 12.5, 4.3, 4));
//        test.createInpatient(new InpatientData(2));
//        test.createSurgical(new SurgicalData(Timestamp.valueOf("2007-08-23 09:10:10"), "Mrs K", 22.2, 100, 76, 4));
//        test.createSurgical(new SurgicalData(1));
//        test.createMedication(new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "peni", 20, 5, 5));
//        test.createMedication(new MedicationData(2));
//        test.findByPatientID(1);
//        test.findByPatientID_M(1);
//        test.findByPatientID_I(1);
//        test.findByPatientID_S(3);
//        test.findByLN("Kent");
//        test.findAll();
//        test.Update(new PatientData("Hello", "JJ", "Anxiety", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0")),2 );
//        test.Update(new InpatientData(Timestamp.valueOf("2011-01-23 09:10:10.0"), "12", 60.0, 12.5, 4.3, 4), 21);
//        test.Update(new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "moto", 20, 5, 5), 1);
//        test.Update(new SurgicalData(Timestamp.valueOf("2000-08-23 09:10:10"), "Thomas", 92.2, 100, 76, 4), 1);
//        test.deleteInpatient(2);
//        test.deleteMedication(1);
//        test.deleteSurgical(3);
//        test.deletePatient(2);
//        System.out.println(test.findAll("inpatient"));
//        test.metaColumn();
//        System.out.println(test.report(2)); 
//        String query = "select (sum(roomfee) + sum(surgeonfee) + sum(SUPPLIES)) as cost from surgical WHERE patientid = ?";
//        System.out.println(test.runQuery(query, 2));
          test.findByInpatientID(2);
    }
}
