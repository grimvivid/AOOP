package com.mycompany.motorphgui2.dao;

import com.mycompany.motorphgui2.entity.Employee;
import com.mycompany.motorphgui2.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements Dao<Employee, Integer> {

    @Override
    public Optional<Employee> get(Integer id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Employee.class, id));
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }

    @Override
    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to save employee.", e);
        }
    }

    @Override
    public void update(Employee employee) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.merge(employee);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback(); // 🔁 Only rollback if it's still active
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to update employee.", e);

        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // ✅ Explicitly close session
            }
        }
    }

    @Override
    public void delete(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to delete employee.", e);
        }
    }

    // Optional custom query
    public List<Employee> findByDepartment(String department) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee WHERE department = :dept", Employee.class)
                          .setParameter("dept", department)
                          .list();
        }
    }
}
