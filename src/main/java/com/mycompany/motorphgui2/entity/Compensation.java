package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compensation")
public class Compensation {
    @Id
    @Column(name = "SalaryID")
    private int salaryId;

    @OneToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "BankAccountNumber")
    private String bankAccountNumber;

    @Column(name = "BasicSalary", precision = 10, scale = 2, nullable = false)
    private BigDecimal basicSalary;

    @Column(name = "RiceSubsidy", precision = 10, scale = 2)
    private BigDecimal riceSubsidy = BigDecimal.ZERO;

    @Column(name = "PhoneAllowance", precision = 10, scale = 2)
    private BigDecimal phoneAllowance = BigDecimal.ZERO;

    @Column(name = "ClothingAllowance", precision = 10, scale = 2)
    private BigDecimal clothingAllowance = BigDecimal.ZERO;

    @Column(name = "GrossSemiMonthlyRate", precision = 10, scale = 2)
    private BigDecimal grossSemiMonthlyRate;

    @Column(name = "HourlyRate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    // Getters and setters
    public int getSalaryId() { return salaryId; }
    public void setSalaryId(int salaryId) { this.salaryId = salaryId; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    
    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
    
    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }
    
    public BigDecimal getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(BigDecimal riceSubsidy) { this.riceSubsidy = riceSubsidy; }
    
    public BigDecimal getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(BigDecimal phoneAllowance) { this.phoneAllowance = phoneAllowance; }
    
    public BigDecimal getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(BigDecimal clothingAllowance) { this.clothingAllowance = clothingAllowance; }
    
    public BigDecimal getGrossSemiMonthlyRate() { return grossSemiMonthlyRate; }
    public void setGrossSemiMonthlyRate(BigDecimal grossSemiMonthlyRate) { this.grossSemiMonthlyRate = grossSemiMonthlyRate; }
    
    public BigDecimal getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(BigDecimal hourlyRate) { this.hourlyRate = hourlyRate; }
}