/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2;

/**
 *
 * @author DAVID
 */
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDataUtil {
    private static final String FILENAME = "MotorPH Employee Data.csv";

    public static Staff fetchEmployeeDetails(String employeenum) {
        Staff staff = new Staff();
        staff.setEmployeeNumber(employeenum);

        try (CSVReader csvreader = new CSVReader(new FileReader(FILENAME))) {
            String[] line;
            while ((line = csvreader.readNext()) != null) {
                if (line[0].equals(employeenum)) {
                    populateStaffDetails(staff, line);
                    break;
                }
            }
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(EmployeeDataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staff;
    }

    private static void populateStaffDetails(Staff staff, String[] data) {
        staff.setLastName(data[1]);
        staff.setFirstName(data[2]);
        staff.setBirthday(data[3]);
        staff.setAddress(data[4]);
        staff.setPhoneNumber(data[5]);
        staff.setSSSNumber(data[6]);
        staff.setPhilHealthNumber(data[7]);
        staff.setTIN(data[8]);
        staff.setPagibigNumber(data[9]);
        staff.setStatus(data[10]);
        staff.setPosition(data[11]);
        staff.setSuperior(data[12]);
        staff.setBasic(data[13]);
        staff.setRiceAllowance(data[14]);
        staff.setPhoneAllowance(data[15]);
        staff.setClothAllowance(data[16]);
        staff.setSemiMonthlyRate(data[17]);
        staff.setHourlyRate(data[18]);
    }
}