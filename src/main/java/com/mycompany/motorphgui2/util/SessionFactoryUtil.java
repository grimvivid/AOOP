package com.mycompany.motorphgui2.util;

import com.mycompany.motorphgui2.entity.*; // ✅ Import all your entity classes
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // Loads DB properties only
                .applySetting("hibernate.c3p0.checkoutTimeout", "3000")
                .build();

            MetadataSources sources = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Compensation.class)
                .addAnnotatedClass(SSS.class)
                .addAnnotatedClass(PhilHealth.class)
                .addAnnotatedClass(Pagibig.class)
                .addAnnotatedClass(Tax.class)
                .addAnnotatedClass(Payroll.class)
                .addAnnotatedClass(Deduction.class)
                .addAnnotatedClass(LeaveRequest.class)
                .addAnnotatedClass(PayPeriod.class)
                .addAnnotatedClass(Attendance.class);

            Metadata metadata = sources.getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory closed");
        }
    }
}
