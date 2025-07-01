package com.mycompany.motorphgui2;

import java.util.Set;
import java.util.EnumSet;

public enum Role {
    // HR Roles (HR Team Leader merged into HR Rank and File)
    HR_TEAM_LEADER(EnumSet.of(Permission.VIEW_PAYROLL,Permission.CreateLogIn,Permission.ProcessLeave)),
    HR_RANK_AND_FILE(EnumSet.of(Permission.VIEW_PAYROLL)),
    HR_MANAGER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION, Permission.GENERATE_REPORT, Permission.Delete, Permission.AddLoginUser,Permission.Update, Permission.AddEmployee, Permission.CreateLogIn, Permission.ProcessLeave)),

    // Payroll Roles
    PAYROLL_RANK_AND_FILE(EnumSet.of(Permission.VIEW_PAYROLL)),
    PAYROLL_TEAM_LEADER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION)),
    PAYROLL_MANAGER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION, Permission.APPLY_TO_PAYROLL)),

    // Account Roles
    ACCOUNT_RANK_AND_FILE(EnumSet.of(Permission.VIEW_PAYROLL)),
    ACCOUNT_TEAM_LEADER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION)),
    ACCOUNT_MANAGER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION, Permission.APPLY_TO_PAYROLL, Permission.Delete, Permission.AddLoginUser, Permission.Update, Permission.AddEmployee)),

    // Finance Role
    FINANCE_MANAGER(EnumSet.of(Permission.VIEW_PAYROLL, Permission.VALIDATE_TRANSACTION, Permission.APPLY_TO_PAYROLL, Permission.GENERATE_REPORT));
    
//    STAFF

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(Role currentUserRole, Permission permission) {
        return permissions.contains(permission);
    }

}
