/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2;

/**
 *
 * @author DAVID
 */
import javax.swing.*;

public class FormUtil {

    public static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void clearComboBoxes(JComboBox<?>... comboBoxes) {
        for (JComboBox<?> comboBox : comboBoxes) {
            comboBox.setSelectedIndex(0);
        }
    }
}
