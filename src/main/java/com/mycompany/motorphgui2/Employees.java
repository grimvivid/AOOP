/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.motorphgui2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public abstract class Employees {
    
    protected String employeeNumber, firstName, lastName, birthday, address, phoneNumber,
            SSSNumber, philHealthNumber, tin, pagibigNumber, status, position, superior,
            basic, riceAllowance, phoneAllowance, clothAllowance, semiMonthlyRate, hourlyRate; 
    

    public Employees() {}
    
    public Employees(String _employeeNumber, String _firstName, String _lastName, String _birthday, String _address,
                     String _phoneNumber, String _SSSNumber, String _philHealthNumber, String _tin, String _pagibigNumber,
                     String _status, String _position, String _superior, String _basic, String _riceAllowance,
                     String _phoneAllowance, String _clothAllowance, String _semiMonthlyRate, String _hourlyRate) {

        // Call validation methods before assigning
        this.employeeNumber = validateEmployeeNumber(_employeeNumber);
        this.firstName = validateName(_firstName, "First Name");
        this.lastName = validateName(_lastName, "Last Name");
        this.birthday = validateDate(_birthday);
        this.address = validateAddress(_address);
        this.phoneNumber = validatePhoneNumber(_phoneNumber);
        this.SSSNumber = validateSSSNumber(_SSSNumber);
        this.philHealthNumber = validatePhilHealthNumber(_philHealthNumber);
        this.tin = validateTIN(_tin);
        this.pagibigNumber = validatePagibigNumber(_pagibigNumber);
        this.status = validateStatus(_status);
        this.position = validatePosition(_position);
        this.superior = validateName(_superior, "Superior");
        this.basic = validateNumber(_basic, "Basic Salary");
        this.riceAllowance = validateNumber(_riceAllowance, "Rice Allowance");
        this.phoneAllowance = validateNumber(_phoneAllowance, "Phone Allowance");
        this.clothAllowance = validateNumber(_clothAllowance, "Cloth Allowance");
        this.semiMonthlyRate = validateNumber(_semiMonthlyRate, "Semi-Monthly Rate");
        this.hourlyRate = validateNumber(_hourlyRate, "Hourly Rate");
    }

    // === VALIDATION METHODS ===

    public String validateEmployeeNumber(String _employeeNumber) {
        if (_employeeNumber.matches("^\\d{5}$")) { // 5-digit Employee Number
            return _employeeNumber;
        } else {
            throw new IllegalArgumentException("Invalid Employee Number! Must be exactly 5 digits.");
        }
    }

    public String validateName(String name, String fieldName) {
    if (name.matches("^[a-zA-Z\\s\\-'/,]+$")) { // Allows letters, spaces, hyphens, apostrophes, and slashes
        return name.trim();
    } else {
        throw new IllegalArgumentException(fieldName + " must contain only letters, spaces, hyphens, apostrophes, and slashes." + "your input is" + name);
        }
    }
    public String validateDate(String date) {
        if (date.matches("^\\d{2}/\\d{2}/\\d{4}$")) { // Format: MM/dd/yyyy
            return date;
        } else {
            throw new IllegalArgumentException("Invalid Date! Use MM/dd/yyyy format.");
        }
    }

    public String validateAddress(String address) {
        if (address.length() <= 100) {
            return address.trim();
        } else {
            throw new IllegalArgumentException("Address is too long! Max 100 characters.");
        }
    }

    public String validatePhoneNumber(String phone) {
        if (phone.matches("^\\d{8,15}$")) { // Matches 3-3-3 format
            return phone;
        } else {
            throw new IllegalArgumentException("Invalid Phone Number! Must be in 10-12 digits.");
        }
    }

   public String validateSSSNumber(String sss) {
    if (sss.matches("^\\d{2}-\\d{7}-\\d{1}$")) { // Matches 2-7-1 digit format with hyphens
        return sss;
    } else {
        throw new IllegalArgumentException("Invalid SSS Number! Must be in format XX-XXXXXXX-X.");
    }
}


    public String validatePhilHealthNumber(String _philHealthNumber) {
        if (_philHealthNumber.matches("^\\d{12}$")) { // Exactly 12 digits
            return _philHealthNumber;
        } else {
            throw new IllegalArgumentException("Invalid PhilHealth Number! Must be 12 digits.");
        }
    }

    public String validateTIN(String tin) {
        if (tin.matches("^\\d{3}-\\d{3}-\\d{3}-\\d{3}$")) { // Format: 000-000-000-000
            return tin;
        } else {
            throw new IllegalArgumentException("Invalid TIN! Must follow the format XXX-XXX-XXX-XXX and contain 12 digits.");
        }
    }


    public String validatePagibigNumber(String pagibig) {
       if (pagibig.matches("^\\d{12}$")) { // 12 digits only
           return pagibig;
       } else {
           throw new IllegalArgumentException("Invalid Pag-IBIG Number! Must be 12 digits.");
       }
   }

    public String validateStatus(String status) {
        if (status.equalsIgnoreCase("Regular") || status.equalsIgnoreCase("Probationary") ||
            status.equalsIgnoreCase("Contractual")) {
            return status;
        } else {
            throw new IllegalArgumentException("Invalid Status! Choose Regular, Probationary, or Contractual.");
        }
    }

    public String validatePosition(String position) {
        if (!position.isEmpty()) {
            return position.trim();
        } else {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
    }

    public String validateNumber(String number, String fieldName) {
        try {
            Double.parseDouble(number); // Check if it's a valid number

            // Remove the decimal to count total digits
            String cleanNumber = number.replace(".", "");

            // Check length between 3 to 10 digits
            if ((number.equals("0") || number.matches("^\\d{1,9}(\\.\\d{1,2})?$")) && cleanNumber.length() >= 1 && cleanNumber.length() <= 10) {
                return number;
            } else {
                throw new IllegalArgumentException(fieldName + " must be between 3 to 10 digits (including decimals).");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid numeric value.");
        }
    }
public String validateLeaveDate(String start, String end) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        LocalDate currentDate = LocalDate.now(); // Get today's date

        //  Prevent applying leave for past dates
        if (!startDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("Start date must be tomorrow or later. Selected date: " + start);
        }

        //  End date should not be before start date
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be earlier than start date. Selected dates: " + start + " to " + end);
        }

        //  Check if leave duration exceeds 10 days
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1; // Inclusive of both start and end date
        if (daysBetween > 10) {
            throw new IllegalArgumentException("Leave duration cannot exceed 10 days. Duration selected: " + daysBetween + " days.");
        }

        return String.valueOf(daysBetween); // Return valid leave duration
    } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Invalid date format. Use MM/dd/yyyy. \nError in parsing: " + start + " or " + end);
    }
}
    
    
    
    

    // === GETTERS AND SETTERS ===
    
    public void setEmployeeNumber(String _employeeNumber) { employeeNumber = validateEmployeeNumber(_employeeNumber); }
    public void setFirstName(String _firstName) { firstName = validateName(_firstName, "First Name"); }
    public void setLastName(String _lastName) { lastName = validateName(_lastName, "Last Name"); }
    public void setBirthday(String _birthday) { birthday = validateDate(_birthday); }
    public void setAddress(String _address) { address = validateAddress(_address); }
    public void setPhoneNumber(String _phoneNumber) { phoneNumber = validatePhoneNumber(_phoneNumber); }
    public void setSSSNumber(String _SSSNumber) { SSSNumber = validateSSSNumber(_SSSNumber); }
    public void setPhilHealthNumber(String _philHealthNumber) { philHealthNumber = validatePhilHealthNumber(_philHealthNumber); }
    public void setTIN(String _tin) { tin = validateTIN(_tin); }
    public void setPagibigNumber(String _pagibigNumber) { pagibigNumber = validatePagibigNumber(_pagibigNumber); }
    public void setStatus(String _status) { status = validateStatus(_status); }
    public void setPosition(String _position) { position = validatePosition(_position); }
    public void setSuperior(String _superior) { superior = validateName(_superior, "Superior"); }
    public void setBasic(String _basic) { basic = validateNumber(_basic, "Basic Salary"); }
    public void setRiceAllowance(String _riceAllowance) { riceAllowance = validateNumber(_riceAllowance, "Rice Allowance"); }
    public void setPhoneAllowance(String _phoneAllowance) { phoneAllowance = validateNumber(_phoneAllowance, "Phone Allowance"); }
    public void setClothAllowance(String _clothAllowance) { clothAllowance = validateNumber(_clothAllowance, "Cloth Allowance"); }
    public void setSemiMonthlyRate(String _semiMonthlyRate) { semiMonthlyRate = validateNumber(_semiMonthlyRate, "Semi-Monthly Rate"); }
    public void setHourlyRate(String _hourlyRate) { hourlyRate = validateNumber(_hourlyRate, "Hourly Rate"); }

    // === GETTERS ===
    public String getEmployeeNumber() { return employeeNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthday() { return birthday; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getSSSNumber() { return SSSNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public String getTIN() { return tin; }
    public String getPagibigNumber() { return pagibigNumber; }
    public String getStatus() { return status; }
    public String getPosition() { return position; }
    public String getSuperior() { return superior; }
    public String getBasic() { return basic; }
    public String getRiceAllowance() { return riceAllowance; }
    public String getPhoneAllowance() { return phoneAllowance; }
    public String getClothAllowance() { return clothAllowance; }
    public String getSemiMonthlyRate() { return semiMonthlyRate; }
    public String getHourlyRate() { return hourlyRate; }

    // === ABSTRACT METHODS ===
    public abstract String computeSalaryEarned(float hoursWorked);
    public abstract String computeGrossSalary(float hoursWorked);
}
