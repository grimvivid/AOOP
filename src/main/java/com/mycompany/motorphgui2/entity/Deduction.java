package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deduction")
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeductionID")
    private int deductionId;
    
    // Many-to-one relationship with Payroll
    @ManyToOne
    @JoinColumn(name = "PayrollID", nullable = false)
    private Payroll payroll;
    
    @Column(name = "DeductionType", nullable = false, length = 50)
    private String deductionType;
    
    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    // Getters and setters
    public int getDeductionId() { return deductionId; }
    public void setDeductionId(int deductionId) { this.deductionId = deductionId; }
    
    public Payroll getPayroll() { return payroll; }
    public void setPayroll(Payroll payroll) { this.payroll = payroll; }
    
    public String getDeductionType() { return deductionType; }
    public void setDeductionType(String deductionType) { this.deductionType = deductionType; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}