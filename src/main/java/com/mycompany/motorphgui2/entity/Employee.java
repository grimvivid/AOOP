package com.mycompany.motorphgui2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    public enum EmploymentStatus {
        REGULAR("Regular"),
        PROBATIONARY("Probationary");

        private final String dbValue;

        EmploymentStatus(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }

        public static EmploymentStatus fromDbValue(String dbValue) {
            return Arrays.stream(values())
                .filter(v -> v.getDbValue().equalsIgnoreCase(dbValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status value: " + dbValue));
        }
    }

    // === Fields ===

    @Id
    @Column(name = "EmployeeID")
    private int employeeId;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "Status", length = 12)
    private String status;

    @Column(name = "Position", nullable = false, length = 50)
    private String position;

    @Column(name = "ImmediateSupervisor", length = 100)
    private String immediateSupervisor;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Compensation compensation;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payroll> payrolls = new ArrayList<>();

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private SSS sss;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private PhilHealth philHealth;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagibig pagibig;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Tax tax;

    // === Constructors ===

    public Employee() {
        // Required no-arg constructor
    }

    public Employee(int employeeId, String firstName, String lastName, String position, EmploymentStatus status) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        setStatus(status);
    }

    // === Getters and Setters ===

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmploymentStatus getStatus() {
        return EmploymentStatus.fromDbValue(this.status);
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status.getDbValue();
    }

    public void setStatus(String status) {
        if (!"Regular".equalsIgnoreCase(status) && !"Probationary".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Status must be either 'Regular' or 'Probationary'");
        }
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public void setImmediateSupervisor(String immediateSupervisor) {
        this.immediateSupervisor = immediateSupervisor;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(Compensation compensation) {
        if (compensation != null) {
            compensation.setEmployee(this);
        } else if (this.compensation != null) {
            this.compensation.setEmployee(null);
        }
        this.compensation = compensation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address != null) {
            address.setEmployee(this);
        } else if (this.address != null) {
            this.address.setEmployee(null);
        }
        this.address = address;
    }

    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public void addPayroll(Payroll payroll) {
        payrolls.add(payroll);
        payroll.setEmployee(this);
    }

    public void removePayroll(Payroll payroll) {
        payrolls.remove(payroll);
        payroll.setEmployee(null);
    }

    public SSS getSss() {
        return sss;
    }

    public void setSss(SSS sss) {
        if (sss != null) {
            sss.setEmployee(this);
        } else if (this.sss != null) {
            this.sss.setEmployee(null);
        }
        this.sss = sss;
    }

    public PhilHealth getPhilHealth() {
        return philHealth;
    }

    public void setPhilHealth(PhilHealth philHealth) {
        if (philHealth != null) {
            philHealth.setEmployee(this);
        } else if (this.philHealth != null) {
            this.philHealth.setEmployee(null);
        }
        this.philHealth = philHealth;
    }

    public Pagibig getPagibig() {
        return pagibig;
    }

    public void setPagibig(Pagibig pagibig) {
        if (pagibig != null) {
            pagibig.setEmployee(this);
        } else if (this.pagibig != null) {
            this.pagibig.setEmployee(null);
        }
        this.pagibig = pagibig;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        if (tax != null) {
            tax.setEmployee(this);
        } else if (this.tax != null) {
            this.tax.setEmployee(null);
        }
        this.tax = tax;
    }

    public String getSssNumber() {
        return sss != null ? sss.getSssNumber() : null;
    }

    public String getPhilHealthNumber() {
        return philHealth != null ? philHealth.getPhilHealthNumber() : null;
    }

    public String getPagibigNumber() {
        return pagibig != null ? pagibig.getPagibigNumber() : null;
    }

    public String getTinNumber() {
        return tax != null ? tax.getTinNumber() : null;
    }

    public int getEmployeeID() {
        return getEmployeeId();
    }

    public int getEmployeeNumber() {
        return getEmployeeId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
               "employeeId=" + employeeId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", position='" + position + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
