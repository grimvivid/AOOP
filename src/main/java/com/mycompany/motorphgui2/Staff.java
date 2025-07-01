package com.mycompany.motorphgui2;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;


import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;

import static java.lang.String.format;

// Concrete Class: Staff
public class Staff extends Employees {

    public Staff(String _employeeNumber, String _firstName, String _lastName, String _birthday, String _address,
                 String _phoneNumber, String _SSSNumber, String _philHealthNumber, String _tin, String _pagibigNumber,
                 String _status, String _position, String _superior, String _basic, String _riceAllowance,
                 String _phoneAllowance, String _clothAllowance, String _semiMonthlyRate, String _hourlyRate) {
            super(_employeeNumber, _firstName, _lastName, _birthday, _address, _phoneNumber, _SSSNumber, _philHealthNumber, _tin, _pagibigNumber,
                   _status, _position, _superior, _basic, _riceAllowance, _phoneAllowance, _clothAllowance, _semiMonthlyRate, _hourlyRate);

        }
    

    public Staff() {

    }


    public DefaultTableModel tableDetails(String filename) throws FileNotFoundException, IOException, CsvValidationException {
        DefaultTableModel details;

        try (CSVReader csvreader = new CSVReader(new FileReader(filename))) {

            // Read header row
            String[] line1 = csvreader.readNext();
            if (line1 == null || line1.length < 10) {  // Ensure there are at least 10 columns
                throw new CsvValidationException("Invalid or empty CSV file: Missing required columns.");
            }

            // Define header columns
            String[] header = {line1[0], line1[1], line1[2], line1[6], line1[7], line1[8], line1[9]};
                details = new DefaultTableModel(header, 0) {
                       @Override
                       public boolean isCellEditable(int row, int column) {
                           return false;  // Disable editing for all cells
                       }
                   };
            String[] line;
            while ((line = csvreader.readNext()) != null) {
                if (line.length >= 10) {  // Ensure there are enough columns in each row
                    String[] data = {line[0], line[1], line[2], line[6], line[7], line[8], line[9]};
                    details.addRow(data);
                } else {
                    System.err.println("Skipping malformed row: " + String.join(", ", line));
                }
            }
        }
        return details;
}
   
    public DefaultTableModel leaveStatus(String filename) throws FileNotFoundException, IOException, CsvValidationException {
        // Define column names for the table model
        String[] columnNames = {"LeaveID", "Employee Number", "Last Name", "Start Date", "End Date", "Number of Days", "Type of Leave", "Reason", "Application Status"};

        // Create the table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;  // Disable editing for all cells
                }
         };

        // Read the CSV file
        try (CSVReader csvReader = new CSVReader(new FileReader(filename))) {
            String[] line;
            csvReader.readNext(); // Skip header row

            // Iterate through CSV rows
            while ((line = csvReader.readNext()) != null) {
                // Extract and arrange the necessary columns
                String leaveID = line[0];             // LeaveID
                String employeeNumber = line[2];      // Employee Number
                String lastName = line[3];            // Last Name
                String startDate = line[7];           // Start Date
                String endDate = line[8];             // End Date
                String numberOfDays = line[6];        // Number of Days
                String typeOfLeave = line[5];         // Type of Leave
                String reason = line[10];             // Reason
                String applicationStatus = line[9];   // Application Status

                // Add the data to the table model
                String[] rowData = {leaveID, employeeNumber, lastName, startDate, endDate, numberOfDays, typeOfLeave, reason, applicationStatus};
                tableModel.addRow(rowData);
            }
        }

        // Return the populated table model
        return tableModel;
}
     

    public DefaultTableModel leaveDetails(String filename) throws FileNotFoundException, IOException, CsvValidationException{
        DefaultTableModel leave;

        try(CSVReader csvreader = new CSVReader(new FileReader(filename))){

            String[] line1 = csvreader.readNext();
            String[] header = {line1[0], line1[1],line1[2],line1[3],line1[4],line1[5] };
             leave = new DefaultTableModel(header, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;  // Disable editing for all cells
                }
            };

            String[] line;

            while((line=csvreader.readNext()) != null){
                String[] data = {line[0], line[1],line[2],line[3],line[4],line[5] };
                leave.addRow(data);
            }
        }
        return leave;
    }

    public void UpdateEmployee(String filename) throws FileNotFoundException, IOException, CsvValidationException{
        String tempfilename = filename.replace(".csv", ".tmp");

        try(CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename,true))){

            String[] line;

            CSVReader csvreader = new CSVReader(new FileReader(filename));

            String[] header =csvreader.readNext();
            csvwriter.writeNext(header);

            while((line=csvreader.readNext())!=null){
                if(line[0].equals(getEmployeeNumber())){
                    line[0] = getEmployeeNumber();
                    line[1] = getLastName();
                    line[2] = getFirstName();
                    line[3] = getBirthday();
                    line[4] = getAddress();
                    line[5] = getPhoneNumber();
                    line[6] = getSSSNumber();
                    line[7] = getPhilHealthNumber();
                    line[8] = getTIN();
                    line[9] = getPagibigNumber();
                    line[10] = getStatus();
                    line[11] = getPosition();
                    line[12] = getSuperior();
                    line[13] = String.valueOf(getBasic());
                    line[14] = String.valueOf(getRiceAllowance());
                    line[15] = String.valueOf(getPhoneAllowance());
                    line[16] = String.valueOf(getClothAllowance());
                    line[17] = String.valueOf(getSemiMonthlyRate());
                    line[18] = String.valueOf(getHourlyRate());

                }
                csvwriter.writeNext(line);

            }

            csvreader.close();

        }
        finally{
            new File(filename).delete();
            new File(tempfilename).renameTo(new File(filename));
        }
    }

    public void AddEmployee(String filename) throws IOException{
        try(CSVWriter csvwriter = new CSVWriter(new FileWriter(filename, true))){
            String[] line = new String[19];

            line[0] = getEmployeeNumber();
            line[1] = getLastName();
            line[2] = getFirstName();
            line[3] = getBirthday();
            line[4] = getAddress();
            line[5] = getPhoneNumber();
            line[6] = getSSSNumber();
            line[7] = getPhilHealthNumber();
            line[8] = getTIN();
            line[9] = getPagibigNumber();
            line[10] = getStatus();
            line[11] = getPosition();
            line[12] = getSuperior();
            line[13] = getBasic();
            line[14] = getRiceAllowance();
            line[15] = getPhoneAllowance();
            line[16] = getClothAllowance();
            line[17] = getSemiMonthlyRate();
            line[18] = getHourlyRate();

            csvwriter.writeNext(line);
        }

    }

    public void DeleteEmployee(String filename) throws FileNotFoundException, IOException, CsvValidationException{
        String tempfilename = filename.replace(".csv", ".tmp");

        CSVReader csvreader = new CSVReader(new FileReader(filename));
        String[] line;

        try(CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename, true))){
            while((line = csvreader.readNext())!= null){
                if(!(line[0].equals(getEmployeeNumber()))){
                    csvwriter.writeNext(line);
                }
            }
            csvreader.close();
        }
        finally{
            new File(filename).delete();
            new File(tempfilename).renameTo(new File(filename));
        }
    }

    public float ComputeHoursWorked(String startDate, String endDate) throws FileNotFoundException, IOException, CsvValidationException, ParseException {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    Date dateIn = dateFormat.parse(startDate);
    Date dateOut = dateFormat.parse(endDate);

    try (CSVReader csvReader = new CSVReader(new FileReader("Attendance Record.csv"))) {

        String[] line;
        ArrayList<Date> dates = new ArrayList<>();
        ArrayList<Date> timein = new ArrayList<>();
        ArrayList<Date> timeout = new ArrayList<>();

        while ((line = csvReader.readNext()) != null) {
            if (line[0].equals(getEmployeeNumber())) {
                Date recordDate = dateFormat.parse(line[1]);
                if (line[2].equals("0:00") || line[3].equals("0:00")) {
                    continue; // Skip absences
                }
                Date inTime = timeFormat.parse(line[2]);
                Date outTime = timeFormat.parse(line[3]);

                dates.add(recordDate);
                timein.add(inTime);
                timeout.add(outTime);
            }
        }

        float totalHours = 0;
        for (int i = 0; i < dates.size(); i++) {
            if (!dates.get(i).before(dateIn) && !dates.get(i).after(dateOut)) {
                float workedHours = (timeout.get(i).getTime() - timein.get(i).getTime()) / (60 * 60 * 1000f);
                totalHours += workedHours >= (47f / 6f) ? 8 : workedHours; // Round to 8 hours if late within 10 minutes
            }
        }

        return totalHours;
    }



    }

    public String computeSSS(){
        double sss;

        float sal = Float.parseFloat(getBasic());


        if(sal<=3250){
            sss = 135;
        }
        else if(sal>3250 & sal<=24750) {
            if(sal%1000==250 |sal%1000==750 ){// this check whether the salary is in the lower/upper bound of range.
                float mod = (sal-3250)%500; // after deducting 3250, in every 500 increment, the sss increases by 22.5
                float multiplier = ((sal-3250-mod)/500);
                sss = 22.5*(multiplier)+135;
            }
            else{// this applies the normal formule is salary falls within the range
                float mod = (sal-3250)%500;
                float multiplier = ((sal-3250-mod)/500);
                sss = 22.5*(multiplier+1)+135;
            }
        }
        else{
            sss = 1125;
        }

        return format("%.2f",sss);
    }

    public String computePH(){
        double ph;

        float sal = Float.parseFloat(getBasic());


        if(sal<=10000){//if-elseif-else statement checks the range of salary and applies formula in each range
            ph = 300/2;
        }
        else if (sal>10000 & sal<60000){
            ph = sal*(0.03)/2;
        }
        else{
            ph = 1800/2;
        }

        return format("%.2f",ph);
    }

    public String computePGB(){
        double pagibig;
        float sal = Float.parseFloat(getBasic());

        if(sal>1000 & sal<=1500){//if-else statement checks the range of salary and applies formula in each rang
            pagibig = sal*(float)0.01;
        }
        else{
            if(sal*0.02 <100){// this nested if-else statement provides the pagibig to be 100 if the values exceeds 100
                pagibig= sal*(float)0.02;
            }
            else{
                pagibig = 100;
            }
        }

        return format("%.2f",pagibig);
    }

    public String computeTax(){
        float tax;
        float sal = Float.parseFloat(getBasic());
        float sss = Float.parseFloat(computeSSS());
        float pagibig = Float.parseFloat(computePGB());
        float ph = Float.parseFloat(computePH());

        float taxable = sal-sss-pagibig-ph;

        if(sal <=20832){//if-elseif-else statement checks the range of taxable income and applies formula in each range
            tax = 0;
        }
        else if(sal>20832 & sal<33333){
            tax = (float) ((taxable-20833)*0.2);
        }
        else if(sal>=33333 & sal< 66667){
            tax = (float) ((taxable-33333)*0.25+2500);
        }
        else if(sal>=66667 & sal< 166667){
            tax = (float) ((taxable-66667)*0.3+10833);
        }
        else if(sal>=166667 & sal< 666667){
            tax = (float) ((taxable-166667)*0.32+40833.33);
        }
        else{
            tax = (float) ((sal-666667)*0.35+200833.33);
        }


        return format("%.2f",tax);
    }

    public String computeTotalDeduct(){

        float tax = Float.parseFloat(computeTax());
        float sss = Float.parseFloat(computeSSS());
        float pagibig = Float.parseFloat(computePGB());
        float ph = Float.parseFloat(computePH());

        return format("%.2f",sss+ph+pagibig+tax);
    }

    public String computeNet(float hw){
        float net = Float.parseFloat(computeGrossSalary(hw))-Float.parseFloat(computeTotalDeduct());
        return format("%.2f",net);
    }

    public boolean LeaveIsAllowed(String filename,String leaveType, String days) throws FileNotFoundException, IOException, CsvValidationException{

        try(CSVReader csvreader = new CSVReader(new FileReader(filename))){

            String[] line;
            int leaveDays = Integer.parseInt(days);
            while((line=csvreader.readNext())!=null){
                if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Sick Leave")){
                    int remainSL = Integer.parseInt(line[3]);

                    if(leaveDays>remainSL){
                        return false;
                    }
                }
                else if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Vacation Leave")){
                    int remainVL = Integer.parseInt(line[4]);

                    if(leaveDays>remainVL){
                        return false;
                    }
                }
                else if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Sick Leave")){
                    int remainEL = Integer.parseInt(line[3]);

                    if(leaveDays>remainEL){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public void ApplyLeave(String filename, String leaveType, String days) throws FileNotFoundException, IOException, CsvValidationException {

        String tempfilename = filename.replace(".csv", ".tmp");

        try(CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename,true))){

            String[] line;


            CSVReader csvreader = new CSVReader(new FileReader(filename));

            String[] header =csvreader.readNext();
            csvwriter.writeNext(header);

            while((line=csvreader.readNext())!=null){
                if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Sick Leave")){

                    line[3] = String.valueOf(Integer.parseInt(line[3])-Integer.parseInt(days));

                }
                else if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Vacation Leave")){
                    line[4] = String.valueOf(Integer.parseInt(line[4])-Integer.parseInt(days));
                }
                else if(line[0].equals(getEmployeeNumber()) & leaveType.equals("Sick Leave")){
                    line[5] = String.valueOf(Integer.parseInt(line[5])-Integer.parseInt(days));
                }


                csvwriter.writeNext(line);

            }

            csvreader.close();

        }
        finally{
            new File(filename).delete();
            new File(tempfilename).renameTo(new File(filename));
        }

    }

    public void createLeaveApplication(String dateFiled, String leaveType, String days, String start, String end, String reason) throws IOException, CsvValidationException {
String file = "Remaining_Leave.csv";
    File leaveFile = new File("Leave Application.csv");
    boolean fileExists = leaveFile.exists();

    // ✅ Get the next LeaveID with auto-increment
    String nextLeaveID = getNextLeaveID(leaveFile);

    try (CSVWriter csvwriter = new CSVWriter(new FileWriter(leaveFile, true))) {
        // Write headers if file is new
        if (!fileExists) {
            String[] header = {"LeaveID", "Date Filed", "Employee Number", "Last Name", "First Name", "Type of Leave Applied",
                    "Number of Days", "Start Date", "End Date", "Application Status", "Reason"};
            csvwriter.writeNext(header);
        }

        // Write data row with LeaveID
        String[] dataRow = {nextLeaveID, dateFiled, getEmployeeNumber(), getLastName(), getFirstName(),
                leaveType, days, start, end, "Filed", reason};

        csvwriter.writeNext(dataRow);
    }
}
        private String getNextLeaveID(File leaveFile) throws IOException, CsvValidationException {
            int nextID = 1; // Default for first entry

            if (leaveFile.exists()) {
                try (CSVReader csvReader = new CSVReader(new FileReader(leaveFile))) {
                    String[] line;
                    csvReader.readNext(); // Skip header

                    while ((line = csvReader.readNext()) != null) {
                        String currentID = line[0]; // Get LeaveID from first column
                        try {
                            int parsedID = Integer.parseInt(currentID);
                            if (parsedID >= nextID) {
                                nextID = parsedID + 1; // Increment LeaveID
                            }
                        } catch (NumberFormatException e) {
                            // Ignore invalid IDs if present
                        }
                    }
                }
            }

            // Format nextID with 5-digit padding
            return String.valueOf(nextID);
        }



    @Override
    public String computeSalaryEarned(float hw){
        float salEarned= Float.parseFloat(getHourlyRate())*hw;
        return format("%.2f",salEarned);
    }

    @Override
    public String computeGrossSalary(float hw){
        float gross =  Float.parseFloat(computeSalaryEarned(hw))+
                Float.parseFloat(getRiceAllowance())+
                Float.parseFloat(getPhoneAllowance())+
                Float.parseFloat(getClothAllowance());

        return format("%.2f",gross);
    }
}

