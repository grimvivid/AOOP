/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2.dao;

import com.mycompany.motorphgui2.entity.Attendance;
import java.util.Optional;

public class AttendanceDaoTest {
    public static void main(String[] args) {
        AttendanceDaoImpl dao = new AttendanceDaoImpl();
        Optional<Attendance> attendance = dao.get(1);

        if (attendance.isPresent()) {
            System.out.println("✅ Attendance found:\n" + attendance.get());
        } else {
            System.out.println("❌ Attendance with ID 1 not found.");
        }
    }
}
