package com.mycompany.motorphgui2.test;

import com.mycompany.motorphgui2.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import jakarta.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateConnectionTest {

    @Test
    public void testDatabaseConnection() {
        System.out.println("Testing Hibernate connection...");

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            // Test simple query
            Object result = session.createNativeQuery("SELECT 1").getSingleResult();
            assertEquals(1, ((Number) result).intValue());
            System.out.println("Basic SELECT 1 test passed.");

            // Test database name (you can adjust this)
            String dbName = (String) session.createNativeQuery("SELECT DATABASE()").getSingleResult();
            System.out.println("Connected to database: " + dbName);
            assertEquals("payrollsystem_db", dbName, "Expected database name mismatch.");

            // Check if employees table has data
            Object countResult = session.createNativeQuery("SELECT COUNT(*) FROM employee").getSingleResult();
            Long employeeCount = ((Number) countResult).longValue();
            System.out.println("Found " + employeeCount + " employee(s).");
            assertTrue(employeeCount >= 0, "Employee table should exist.");

            System.out.println("Hibernate connection test successful!");

        } catch (NoResultException nre) {
            fail("Query returned no result: " + nre.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Database connection test failed: " + e.getMessage());
        }
    }
}
