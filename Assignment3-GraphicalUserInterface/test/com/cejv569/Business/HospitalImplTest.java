/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cejv569.Business;

import com.cejv569.Business.HospitalImpl;
import com.cejv569.Data.InpatientData;
import com.cejv569.Data.MedicationData;
import com.cejv569.Data.PatientData;
import com.cejv569.Data.SurgicalData;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Timestamp;

/**
 *
 * @author gina0
 */
public class HospitalImplTest {
    
    public HospitalImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createPatient method, of class HospitalImpl.
     */
    @Test
    public void testCreatePatient() throws Exception {
        System.out.println("createPatient");
        PatientData patientData = new PatientData("Hello", "Ko", "JAVAobsessed", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0"));
        HospitalImpl instance = new HospitalImpl();
        int result = instance.createPatient(patientData);
        assertEquals(6, instance.findAll().size());
    }

    /**
     * Test of createInpatient method, of class HospitalImpl.
     */
    
    @Test
    public void testCreateInpatient() throws Exception {
        System.out.println("createInpatient");
        InpatientData inPatientData = new InpatientData(Timestamp.valueOf("2011-01-23 09:10:10.0"), "12", 60.0, 12.5, 4.3, 4);
        HospitalImpl instance = new HospitalImpl();
        int result = instance.createInpatient(inPatientData);
        assertEquals(21, instance.findAll("inpatient"));
    }
//
//    /**
//     * Test of createSurgical method, of class HospitalImpl.
//     */
    @Test
    public void testCreateSurgical() throws Exception {
        System.out.println("createSurgical");
        SurgicalData surgicalData = new SurgicalData(Timestamp.valueOf("2007-08-23 09:10:10"), "Mrs K", 22.2, 100, 76, 4);
        HospitalImpl instance = new HospitalImpl();
        int result = instance.createSurgical(surgicalData);
        assertEquals(6, instance.findAll("surgical"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of createMedication method, of class HospitalImpl.
     */
    @Test
    public void testCreateMedication() throws Exception {
        System.out.println("createMedication");
        MedicationData medicationData = new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "Moto", 20, 5, 5);
        HospitalImpl instance = new HospitalImpl();
        int result = instance.createMedication(medicationData);
        assertEquals(6, instance.findAll("medication"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPatientID method, of class HospitalImpl.
     */
    @Test
    public void testFindByPatientID() throws Exception {
        System.out.println("findByPatientID");
        int patientID = 1;
        HospitalImpl instance = new HospitalImpl();
        PatientData result = instance.findByPatientID(patientID);
        assertEquals("Bruce", result.getFirstName());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

   

    /**
     * Test of findByLN method, of class HospitalImpl.
     */
    @Test
    public void testFindByLN() throws Exception {
        System.out.println("findByLN");
        String lastName = "Allen";
        HospitalImpl instance = new HospitalImpl();
        ArrayList<PatientData> result = instance.findByLN(lastName);
        assertEquals("Barry", result.get(0).getFirstName());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class HospitalImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        HospitalImpl instance = new HospitalImpl();
        ArrayList<PatientData> result = instance.findAll();
        assertEquals(5, result.size());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class HospitalImpl.
     */
    @Test
    public void testUpdate_PatientData_int() throws Exception {
        System.out.println("Update");
        PatientData patientData = new PatientData("Hello", "JJ", "Anxiety", Timestamp.valueOf("2007-08-23 09:10:10.0"), Timestamp.valueOf("2008-09-23 10:10:10.0"));
        int id = 2;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.Update(patientData, id);
        assertEquals("Hello", instance.findByPatientID(id).getLastName());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class HospitalImpl.
     */
    @Test
    public void testUpdate_InpatientData_int() throws Exception {
        System.out.println("Update");
        InpatientData inpatientData = new InpatientData(Timestamp.valueOf("2011-01-23 09:10:10.0"), "TPI", 60.0, 12.5, 4.3, 2);
        int inpatientID = 4;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.Update(inpatientData, inpatientID);
        assertEquals("TPI", instance.findByPatientID_I(2).get(0).getRoomNumber());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class HospitalImpl.
     */
    @Test
    public void testUpdate_MedicationData_int() throws Exception {
        System.out.println("Update");
        MedicationData medicationData = new MedicationData(Timestamp.valueOf("2007-11-02 12:15:20"), "moto", 20, 5, 5);
        int medicationID = 1;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.Update(medicationData, medicationID);
        assertEquals("moto", instance.findByPatientID_M(5).get(0).getMed());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class HospitalImpl.
     */
    @Test
    public void testUpdate_SurgicalData_int() throws Exception {
        System.out.println("Update");
        SurgicalData surgicalData = new SurgicalData(Timestamp.valueOf("2000-08-23 09:10:10"), "Thomas", 92.2, 100, 76, 4);
        int surgicalID = 4;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.Update(surgicalData, surgicalID);
        assertEquals("Thomas", instance.findByPatientID_S(4).get(0).getSurgery());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteInpatient method, of class HospitalImpl.
     */
    
    
    @Test
    public void testDeleteInpatient() throws Exception {
        System.out.println("deleteInpatient");
        int id = 5;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.deleteInpatient(id);
        assertEquals(17, instance.findAll("inpatient"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMedication method, of class HospitalImpl.
     */
    @Test
    public void testDeleteMedication() throws Exception {
        System.out.println("deleteMedication");
        int id = 2;
        HospitalImpl instance = new HospitalImpl();
        int expResult = 0;
        int result = instance.deleteMedication(2);
        assertEquals(5, instance.findAll("medication"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSurgical method, of class HospitalImpl.
     */
    @Test
    public void testDeleteSurgical() throws Exception {
        System.out.println("deleteSurgical");
        int id = 3;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.deleteSurgical(id);
        assertEquals(6, instance.findAll("medication"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePatient method, of class HospitalImpl.
     */
    @Test
    public void testDeletePatient() throws Exception {
        System.out.println("deletePatient");
        int patientID = 1;
        HospitalImpl instance = new HospitalImpl();
        int result = instance.deletePatient(patientID);
        assertEquals(5, instance.findAll().size());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class HospitalImpl.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = {"HI"};
        HospitalImpl.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
