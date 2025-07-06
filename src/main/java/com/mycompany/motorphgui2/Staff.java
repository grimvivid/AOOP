package com.mycompany.motorphgui2;

import com.mycompany.motorphgui2.entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.lang.String.format;

public class Staff extends Employees {

    public Staff() {}

    public Staff(String _employeeNumber, String _firstName, String _lastName, String _birthday, String _address,
                 String _phoneNumber, String _SSSNumber, String _philHealthNumber, String _tin, String _pagibigNumber,
                 String _status, String _position, String _superior, String _basic, String _riceAllowance,
                 String _phoneAllowance, String _clothAllowance, String _semiMonthlyRate, String _hourlyRate) {
        super(_employeeNumber, _firstName, _lastName, _birthday, _address, _phoneNumber,
                _SSSNumber, _philHealthNumber, _tin, _pagibigNumber, _status, _position, _superior,
                _basic, _riceAllowance, _phoneAllowance, _clothAllowance, _semiMonthlyRate, _hourlyRate);
    }

    public Employee toEmployeeEntity() {
        Employee employee = new Employee();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedBirthday = LocalDate.parse(getBirthday(), formatter);

        employee.setEmployeeId(Integer.parseInt(getEmployeeNumber()));
        employee.setFirstName(getFirstName());
        employee.setLastName(getLastName());
        employee.setBirthday(parsedBirthday);
        employee.setPhoneNumber(getPhoneNumber());
        employee.setStatus(getStatus());
        employee.setPosition(getPosition());
        employee.setImmediateSupervisor(getSuperior());

        Address address = new Address();
        address.setStreet(getAddress());
        address.setCity("N/A");       // dummy value
        address.setProvince("N/A");
        address.setZipCode("0000");
        address.setEmployee(employee);
        employee.setAddress(address);

        Compensation compensation = new Compensation();
        compensation.setBasicSalary(parseBigDecimal(getBasic()));
        compensation.setRiceSubsidy(parseBigDecimal(getRiceAllowance()));
        compensation.setPhoneAllowance(parseBigDecimal(getPhoneAllowance()));
        compensation.setClothingAllowance(parseBigDecimal(getClothAllowance()));
        compensation.setGrossSemiMonthlyRate(parseBigDecimal(getSemiMonthlyRate()));
        compensation.setHourlyRate(parseBigDecimal(getHourlyRate()));
        compensation.setEmployee(employee);
        employee.setCompensation(compensation);

        SSS sss = new SSS();
        sss.setSssNumber(getSSSNumber());
        sss.setEmployee(employee);
        employee.setSss(sss);

        PhilHealth ph = new PhilHealth();
        ph.setPhilHealthNumber(getPhilHealthNumber());
        ph.setEmployee(employee);
        employee.setPhilHealth(ph);

        Tax tax = new Tax();
        tax.setTinNumber(getTIN());
        tax.setEmployee(employee);
        employee.setTax(tax);

        Pagibig pagibig = new Pagibig();
        pagibig.setPagibigNumber(getPagibigNumber());
        pagibig.setEmployee(employee);
        employee.setPagibig(pagibig);

        return employee;
    }

    public void populateFromEntity(Employee emp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        setEmployeeNumber(String.valueOf(emp.getEmployeeId()));
        setFirstName(emp.getFirstName());
        setLastName(emp.getLastName());

        if (emp.getBirthday() != null) {
            setBirthday(emp.getBirthday().format(formatter));
        }

        setPhoneNumber(emp.getPhoneNumber() != null ? emp.getPhoneNumber() : "");
        setPosition(emp.getPosition() != null ? emp.getPosition() : "");
        setSuperior(emp.getImmediateSupervisor() != null ? emp.getImmediateSupervisor() : "");
        setStatus(emp.getStatus() != null ? emp.getStatus().getDbValue() : "");

        if (emp.getAddress() != null) {
            setAddress(emp.getAddress().getStreet());
        }

        if (emp.getSss() != null) this.SSSNumber = emp.getSss().getSssNumber();
        if (emp.getPhilHealth() != null) this.philHealthNumber = emp.getPhilHealth().getPhilHealthNumber();
        if (emp.getTax() != null) this.tin = emp.getTax().getTinNumber();
        if (emp.getPagibig() != null) this.pagibigNumber = emp.getPagibig().getPagibigNumber();

        if (emp.getCompensation() != null) {
            setBasic(toStringOrZero(emp.getCompensation().getBasicSalary()));
            setRiceAllowance(toStringOrZero(emp.getCompensation().getRiceSubsidy()));
            setPhoneAllowance(toStringOrZero(emp.getCompensation().getPhoneAllowance()));
            setClothAllowance(toStringOrZero(emp.getCompensation().getClothingAllowance()));
            setSemiMonthlyRate(toStringOrZero(emp.getCompensation().getGrossSemiMonthlyRate()));
            setHourlyRate(toStringOrZero(emp.getCompensation().getHourlyRate()));
        }
    }

    private String toStringOrZero(java.math.BigDecimal val) {
        return val != null ? val.toPlainString() : "0.00";
    }

    private java.math.BigDecimal parseBigDecimal(String value) {
        try {
            return new java.math.BigDecimal(value);
        } catch (Exception e) {
            return java.math.BigDecimal.ZERO;
        }
    }

    // === Computations ===
    public String computeSSS() {
        double sss;
        float sal = Float.parseFloat(getBasic());
        if (sal <= 3250) {
            sss = 135;
        } else if (sal <= 24750) {
            float mod = (sal - 3250) % 500;
            float multiplier = ((sal - 3250 - mod) / 500);
            sss = (sal % 1000 == 250 || sal % 1000 == 750) ? 22.5 * multiplier + 135 : 22.5 * (multiplier + 1) + 135;
        } else {
            sss = 1125;
        }
        return format("%.2f", sss);
    }

    public String computePH() {
        float sal = Float.parseFloat(getBasic());
        double ph = (sal <= 10000) ? 150 : (sal < 60000 ? sal * 0.015 : 900);
        return format("%.2f", ph);
    }

    public String computePGB() {
        float sal = Float.parseFloat(getBasic());
        double pagibig = (sal > 1000 && sal <= 1500) ? sal * 0.01 : Math.min(sal * 0.02, 100);
        return format("%.2f", pagibig);
    }

    public String computeTax() {
        float tax;
        float sal = Float.parseFloat(getBasic());
        float taxable = sal - Float.parseFloat(computeSSS()) - Float.parseFloat(computePH()) - Float.parseFloat(computePGB());
        if (sal <= 20832) {
            tax = 0;
        } else if (sal < 33333) {
            tax = (taxable - 20833) * 0.2f;
        } else if (sal < 66667) {
            tax = (taxable - 33333) * 0.25f + 2500;
        } else if (sal < 166667) {
            tax = (taxable - 66667) * 0.3f + 10833;
        } else if (sal < 666667) {
            tax = (taxable - 166667) * 0.32f + 40833.33f;
        } else {
            tax = (sal - 666667) * 0.35f + 200833.33f;
        }
        return format("%.2f", tax);
    }

    public String computeTotalDeduct() {
        float total = Float.parseFloat(computeSSS()) + Float.parseFloat(computePH()) +
                      Float.parseFloat(computePGB()) + Float.parseFloat(computeTax());
        return format("%.2f", total);
    }

    public String computeNet(float hw) {
        float gross = Float.parseFloat(computeGrossSalary(hw));
        float net = gross - Float.parseFloat(computeTotalDeduct());
        return format("%.2f", net);
    }

    @Override
    public String computeSalaryEarned(float hw) {
        return format("%.2f", Float.parseFloat(getHourlyRate()) * hw);
    }

    @Override
    public String computeGrossSalary(float hw) {
        float gross = Float.parseFloat(computeSalaryEarned(hw)) +
                      Float.parseFloat(getRiceAllowance()) +
                      Float.parseFloat(getPhoneAllowance()) +
                      Float.parseFloat(getClothAllowance());
        return format("%.2f", gross);
    }
  
}


