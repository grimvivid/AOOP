package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payperiod")
public class PayPeriod {
    @Id
    @Column(name = "PayPeriodID")
    private int payPeriodId;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "PeriodLabel")
    private String periodLabel;

    // Getters and setters
    public int getPayPeriodId() { return payPeriodId; }
    public void setPayPeriodId(int payPeriodId) { this.payPeriodId = payPeriodId; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getPeriodLabel() { return periodLabel; }
    public void setPeriodLabel(String periodLabel) { this.periodLabel = periodLabel; }
}