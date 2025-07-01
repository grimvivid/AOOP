package com.mycompany.motorphgui2;

public class LoginResult {
    private final boolean isValid;
    private final String employeeID;
    private final String position;
    private final Role role;
    private static LoginResult currentUser;

    public LoginResult(boolean isValid, String employeeID, String position) {
        this.isValid = isValid;
        this.employeeID = employeeID;
        this.position = position;
        this.role = RoleMapper.getRoleByPosition(position);
        currentUser = this;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPosition() {
        return position;
    }

    public Role getRole() {
        return role;
    }

    public static LoginResult getCurrentUser() { // ✅ Static method to get logged-in user
        return currentUser;
    }
}
