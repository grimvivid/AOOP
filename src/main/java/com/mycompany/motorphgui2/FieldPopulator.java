/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author DAVID
 */
public class FieldPopulator {
    public static void populateEmployeeFields(Staff staff, JTextField[] fields, JComboBox<String> positionCB, JComboBox<String> statusCB) {
        if (staff != null) {
            fields[0].setText(staff.getEmployeeNumber());
            fields[1].setText(staff.getLastName());
            fields[2].setText(staff.getFirstName());
            fields[3].setText(staff.getBirthday());
            fields[4].setText(staff.getAddress());
            fields[5].setText(staff.getPhoneNumber());
            fields[6].setText(staff.getSSSNumber());
            fields[7].setText(staff.getPhilHealthNumber());
            fields[8].setText(staff.getTIN());
            fields[9].setText(staff.getPagibigNumber());
            fields[10].setText(staff.getSuperior()); 
            fields[11].setText(staff.getBasic()); 
            fields[12].setText(staff.getRiceAllowance());
            fields[13].setText(staff.getPhoneAllowance());
            fields[14].setText(staff.getClothAllowance());
            fields[15].setText(staff.getSemiMonthlyRate());
            fields[16].setText(staff.getHourlyRate());

            positionCB.setSelectedItem(staff.getPosition());
            statusCB.setSelectedItem(staff.getStatus());
        }
    }
}