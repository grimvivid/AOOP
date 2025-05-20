package com.mycompany.motorphgui2;

import Frames.CreateUserLogin;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CredentialsManager {
    private static final String CREDENTIALS_FILE = "Credentials.csv";
    private static final String EMPLOYEE_DATA_FILE = "MotorPH Employee Data.csv";

    public static LoginResult validateCredentials(String username, char[] password) throws IOException, CsvValidationException {
        String employeeID = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(CREDENTIALS_FILE))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line[0].equals(username) && Arrays.equals(line[1].toCharArray(), password)) {
                    employeeID = line[2];  // Get employeeID from column C
                    Arrays.fill(password, ' '); // Clear password from memory
                    break;
                }
            }
        }

        if (employeeID == null) {
            return new LoginResult(false, null, null);
        }

        // Find position using employeeID from Employee Data CSV
        String position = getPositionByEmployeeID(employeeID);

        return new LoginResult(true, employeeID, position);
    }






 public void CreateUser(String username, char[] password, String employeeID, Role role) throws IOException, CsvValidationException{
//        if (!CreateUserLogin.hasPermission(role, Permission.AddLoginUser)) {
//            return; // User does not have permission
//        }

        // Check if username already exists
        try (CSVReader csvReader = new CSVReader(new FileReader(CREDENTIALS_FILE))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line[0].equals(username)) {
                    return; // Username already exists
                }
            }
        }

        // Write new user to CSV file
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            String[] line = new String[3]; // Ensuring correct structure

            line[0] = username;
            line[1] = new String(password); // Convert password to string
            line[2] = employeeID;

            csvWriter.writeNext(line);
        }

        // Securely clear password from memory
        Arrays.fill(password, ' ');
    }
    

private static String getPositionByEmployeeID(String employeeID) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(EMPLOYEE_DATA_FILE))) {
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                if (line[0].equals(employeeID)) { // Column A is employeeID
                    return line[11]; // Column L contains position
                }
            }
        }
        return null; // Position not found
    }
}

