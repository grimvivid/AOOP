/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2;


import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

public class LogIn {
    private final String username;
    private final char[] password;

    // Constructor for setting credentials
    public LogIn(String username, char[] password) {
        this.username = username;
        this.password = password.clone(); // Prevent direct reference
    }

    // Getters (Encapsulation)
    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password.clone(); // Prevent exposing internal array
    }

    // Authentication method using external CredentialsManager
    public boolean isAuthenticated() throws IOException, CsvValidationException {
        LoginResult result = CredentialsManager.validateCredentials(username, password);
        return result.isValid(); // Assuming LoginResult has an isValid() method
    }

    // Clear password from memory for security
    public void clearPassword() {
        java.util.Arrays.fill(password, ' ');
    }
}
