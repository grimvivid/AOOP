package com.mycompany.motorphgui2.dao;

import com.mycompany.motorphgui2.entity.Employee;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class EmployeeDaoTest {
    private static EmployeeDaoImpl dao;
    private static Employee testEmployee;

    @BeforeAll
    public static void setup() {
        dao = new EmployeeDaoImpl();
        testEmployee = new Employee();
        testEmployee.setEmployeeId(1001);
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setPosition("IT Specialist");
        testEmployee.setBirthday(LocalDate.of(1990, 1, 1)); // Non-null birthday
        testEmployee.setPhoneNumber("1234567890");
        testEmployee.setImmediateSupervisor("Jane Smith"); // Add if required
        testEmployee.setStatus(Employee.EmploymentStatus.REGULAR);
        dao.save(testEmployee);
    }

    @Test
    public void testGetEmployee() {
        Employee found = dao.get(testEmployee.getEmployeeId()).orElseThrow();
        assertEquals("John", found.getFirstName());
        assertEquals("IT Specialist", found.getPosition());
        assertEquals(Employee.EmploymentStatus.REGULAR, found.getStatus());
        assertNotNull(found.getBirthday()); // Verify birthday was saved
    }

    @Test
    public void testUpdateEmployee() {
        // Test updating status
        testEmployee.setStatus(Employee.EmploymentStatus.PROBATIONARY);
        testEmployee.setBirthday(LocalDate.of(1985, 5, 15)); // Update birthday
        dao.update(testEmployee);
        
        Employee updated = dao.get(testEmployee.getEmployeeId()).orElseThrow();
        assertEquals(Employee.EmploymentStatus.PROBATIONARY, updated.getStatus());
        assertEquals(LocalDate.of(1985, 5, 15), updated.getBirthday());
    }

    @AfterAll
    public static void cleanup() {
        if (testEmployee != null && testEmployee.getEmployeeId() != 0) {
            dao.delete(testEmployee);
        }
    }
}