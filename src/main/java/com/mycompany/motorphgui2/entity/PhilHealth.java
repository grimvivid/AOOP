package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "philhealth")
public class PhilHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhilHealthID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EmployeeID", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "PhilHealthNumber", length = 20, nullable = false, unique = true)
    private String philHealthNumber;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }
}
