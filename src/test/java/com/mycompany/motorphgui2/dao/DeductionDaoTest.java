/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2.dao;

public class DeductionDaoTest {
    public static void main(String[] args) {
        DeductionDaoImpl dao = new DeductionDaoImpl();
        System.out.println("Trying to fetch Deduction with ID 1...");
        dao.get(1).ifPresentOrElse(
            data -> System.out.println("Deduction found: " + data),
            () -> System.out.println("Deduction not found.")
        );
    }
}
