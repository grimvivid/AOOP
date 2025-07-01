package com.mycompany.motorphgui2.dao;

import com.mycompany.motorphgui2.entity.Deduction;
import com.mycompany.motorphgui2.util.SessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class DeductionDaoImpl implements Dao<Deduction, Integer> {

    @Override
    public Optional<Deduction> get(Integer id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Deduction.class, id));
        }
    }

    @Override
    public List<Deduction> getAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Deduction", Deduction.class).list();
        }
    }

    @Override
    public void save(Deduction deduction) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(deduction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(Deduction deduction) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(deduction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Deduction deduction) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(deduction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}