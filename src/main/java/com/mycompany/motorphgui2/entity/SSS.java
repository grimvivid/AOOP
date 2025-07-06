package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sss")
public class SSS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SSSID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EmployeeID", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "SSSNumber", length = 20, nullable = false, unique = true)
    private String sssNumber;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }
}
