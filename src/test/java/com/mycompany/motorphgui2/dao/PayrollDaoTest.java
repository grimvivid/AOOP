/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2.dao;

public class PayrollDaoTest {
    public static void main(String[] args) {
        PayrollDaoImpl dao = new PayrollDaoImpl();
        System.out.println("Trying to fetch Payroll with ID 1...");
        dao.get(1).ifPresentOrElse(
            data -> System.out.println("Payroll found: " + data),
            () -> System.out.println("Payroll not found.")
        );
    }
}