/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2.dao;

public class LeaveRequestDaoTest {
    public static void main(String[] args) {
        LeaveRequestDaoImpl dao = new LeaveRequestDaoImpl();
        System.out.println("Trying to fetch LeaveRequest with ID 1...");
        dao.get(1).ifPresentOrElse(
            data -> System.out.println("LeaveRequest found: " + data),
            () -> System.out.println("LeaveRequest not found.")
        );
    }
}