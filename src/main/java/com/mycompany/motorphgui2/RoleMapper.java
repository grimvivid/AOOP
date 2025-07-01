package com.mycompany.motorphgui2;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class RoleMapper {
    private static final Map<String, Role> positionToRoleMap = new HashMap<>();

    static {
        // HR Roles
        positionToRoleMap.put("HR MANAGER", Role.HR_MANAGER);
        positionToRoleMap.put("HR TEAM LEADER", Role.HR_TEAM_LEADER); // Same as Rank and File
        positionToRoleMap.put("HR RAND AND FILE", Role.HR_RANK_AND_FILE);

        // Payroll Roles
        positionToRoleMap.put("PAYROLL MANAGER", Role.PAYROLL_MANAGER);
        positionToRoleMap.put("PAYROLL TEAM LEADER", Role.PAYROLL_TEAM_LEADER);
        positionToRoleMap.put("PAYROLL RANK AND FILE", Role.PAYROLL_RANK_AND_FILE);

        // Account Roles
        positionToRoleMap.put("ACCOUNT MANAGER", Role.ACCOUNT_MANAGER);
        positionToRoleMap.put("ACCOUNT TEAM LEADER", Role.ACCOUNT_TEAM_LEADER);
        positionToRoleMap.put("ACCOUNT RANK AND FILE", Role.ACCOUNT_RANK_AND_FILE);
    }

    public static Role getRoleByPosition(String position) {
        return positionToRoleMap.getOrDefault(position, Role.PAYROLL_RANK_AND_FILE);
    }
}
//ROLES
//HR_TEAM_LEADER
//HR_RANK_AND_FILE
//HR_MANAGER
//PAYROLL_RANK_AND_FILE
//PAYROLL_TEAM_LEADER
//PAYROLL_MANAGER
//ACCOUNT_RANK_AND_FILE
//ACCOUNT_TEAM_LEADER
//ACCOUNT_MANAGER