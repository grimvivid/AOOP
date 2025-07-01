package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @Column(name = "PayrollID")
    private int payrollId;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "Salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "OvertimeHours")
    private Integer overtimeHours;

    @Column(name = "OvertimeRate", precision = 10, scale = 2)
    private BigDecimal overtimeRate;

    @Column(name = "GrossSalary", precision = 10, scale = 2)
    private BigDecimal grossSalary;

    @Column(name = "Deductions", precision = 10, scale = 2)
    private BigDecimal deductions;

    @Column(name = "NetSalary", precision = 10, scale = 2)
    private BigDecimal netSalary;

    @ManyToOne
    @JoinColumn(name = "PayPeriodID")
    private PayPeriod payPeriod;

    // Getters and setters
    public int getPayrollId() { return payrollId; }
    public void setPayrollId(int payrollId) { this.payrollId = payrollId; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    
    public Integer getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(Integer overtimeHours) { this.overtimeHours = overtimeHours; }
    
    public BigDecimal getOvertimeRate() { return overtimeRate; }
    public void setOvertimeRate(BigDecimal overtimeRate) { this.overtimeRate = overtimeRate; }
    
    public BigDecimal getGrossSalary() { return grossSalary; }
    public void setGrossSalary(BigDecimal grossSalary) { this.grossSalary = grossSalary; }
    
    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }
    
    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }
    
    public PayPeriod getPayPeriod() { return payPeriod; }
    public void setPayPeriod(PayPeriod payPeriod) { this.payPeriod = payPeriod; }
}