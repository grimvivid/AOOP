package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pagibig")
public class Pagibig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PagibigID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EmployeeID", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "PagibigNumber", length = 20, nullable = false, unique = true)
    private String pagibigNumber;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public void setPagibigNumber(String pagibigNumber) {
        this.pagibigNumber = pagibigNumber;
    }
}
